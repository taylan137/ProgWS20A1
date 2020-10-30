package de.ostfalia.prog.ws20;

import java.util.ArrayList;

import de.ostfalia.prog.ws20.enums.Farbe;
import de.ostfalia.prog.ws20.interfaces.IParadiesspiel;

public class Paradiesspiel implements IParadiesspiel {

	Spielbrett[] spielbrett = new Spielbrett[64]; // Spielbrett mit 64 Feldern
	Spieler[] spielerArray; // Array mit allen Spielern
	Farbe[] spielerFarben; // Array mit den Farben von den Spielern
	Farbe farbeAmZug; // Zeigt an welche Farbe grad am Zug ist
	Farbe gewinner = null; // Farbe vom Gewinner

	// Konstruktor ohne Konfiguration
	public Paradiesspiel(Farbe... farben) {
  
		spielerArray = new Spieler[farben.length];
		spielerFarben = farben;

		for (int i = 0; i < spielerArray.length; i++) {
			Spieler spieler = new Spieler(farben[i]);
			spielerArray[i] = spieler;

		}

	}

	// Konstruktor mit Konfiguration
	public Paradiesspiel(String conf, Farbe... farben) {
		// String conf = "GELB-A:30, GELB-B:37, BLAU-B:7"
		// Array mit allen Spielern wird erstellt
		spielerArray = new Spieler[farben.length];
		// farben Array wird erstellt
		spielerFarben = farben;

		// jede Figur bekommt die Position 0 und Spieler werden erstellt
		for (int i = 0; i < spielerArray.length; i++) {
			Spieler spieler = new Spieler(farben[i]);
			spielerArray[i] = spieler;
		}

		// hier wird der String conf zu einem Array
		String[] confArray = Funktion.stringZuArray(conf);

		// der String wird durchgelaufen und die Positionen werden so wie vorgegeben
		// geaendert
		for (int idx = 0; idx < confArray.length; idx += 3) {
			for (Spieler spieler : spielerArray) {
				if (spieler.getFarbe().toString().equals(confArray[idx])) {
					for (Figur figur : spieler.getFigurenListe()) {
						if (figur.getBuchstabe().toString().equals(confArray[idx + 1])) {
							figur.setPosition(Integer.parseInt(confArray[idx + 2]));
						}
					}
				}
			}
		}

	}

//	// spielfeld mit figuren befuellen
//	public void spielfeldFiguren() {
//		for(Spieler spieler : spielerArray) {
//			for(Figur figur : spieler.getFigurenListe()) {
//				spielbrett[figur.getPosition()].setzeFigur(figur);
//			}
//		}
//	}

	@Override
	public Farbe getFarbeAmZug() {
		return farbeAmZug;
	}

	@Override
	public void setFarbeAmZug(Farbe farbe) {
		farbeAmZug = farbe;

	}

	// hier werden alle Spieler und die dazugehoerigen Figuren die sie Besitzen
	// durchgelaufen
	@Override
	public int getFigurposition(String figur) {
		String farbe = figur.substring(0, figur.indexOf('-'));
		String buchstabe = figur.substring(figur.indexOf('-') + 1, figur.length());
		for (Spieler spieler : spielerArray) {
			// Farbe wird verglichen
			if (spieler.getFarbe().toString().equals(farbe)) {
				for (Figur fig : spieler.getFigurenListe()) {
					// Buchstabe wird verglichen
					if (fig.getBuchstabe().toString().equals(buchstabe)) {
						// die Position von der Figur wird wiedergegeben
						return fig.getPosition();
					}
				}
			}
		}
		// wenn nichts gefunden wurde wird -1 zurueckgegeben
		return -1;
	}

	// Methode um figuren zu bewegen
	@Override
	public boolean bewegeFigur(String stringFigur, int... augenzahlen) {
		String farbe = stringFigur.substring(0, stringFigur.indexOf('-'));
		String buchstabe = stringFigur.substring(stringFigur.indexOf('-') + 1, stringFigur.length());

		// Ueberprueft ob der Spieler seine Figur bewegt, wenn ein Spieler
		// von jemanden anderen die Figur bewegen will wird false zurueck gegeben
		if (!(farbeAmZug.toString().equals(farbe))) {
			return false;
		}
		// die Position fon der Figur
		int position = getFigurposition(stringFigur);
		// wenn die Position im Negativen bereich ist, wird False zurueck gegeben
		// wenn die Figur schon am Ziel ist wird false zurueckgegeben damit man die
		// Figur nicht bewegen kann
		// Checkt ob es schon ein Gewinner gibt
		if (getGewinner() != null || position == spielbrett.length - 1 || position < 0) {
			return false;
		}

		// jeder Spieler wird durchgelaufen bis der richtige Spieler gefunden wurde
		for (Spieler spieler : spielerArray) {
			// jede Figur wird durchgelaufen bis die richtige Figur gefunden wurde
			for (Figur figur : spieler.getFigurenListe()) {
				// Farbe wird verglichen
				if (figur.getFarbe().toString().equals(farbe)) {
					// Buchstabe wird verglichen
					if (figur.getBuchstabe().toString().equals(buchstabe)) {
						// neue Position wird ausgerechnet
						int neuePos = Funktion.positionRechner(spielbrett.length - 1, position,
								Funktion.augenzahlenAddieren(augenzahlen));

						// wenn neue Position im Paradies ist wird nochmal gecheckt ob es schon ein
						// gewinner gibt
						if (neuePos == spielbrett.length - 1) {
							getGewinner();
						}
						// Figur bekommt seine neue Position
						figur.setPosition(neuePos);
						return true;
					}
				}
			}
		}
		return false;
	}

	// Methode um den Gewinner zu ermitteln und zurueckzugeben
	@Override
	public Farbe getGewinner() {
		// liste mit Farben
		ArrayList<Farbe> gewonnen = new ArrayList<>();
		// Spieler werden wieder durchgelaufen
		for (Spieler spieler : spielerArray) {
			// Figuren werden wieder durchgelaufen
			for (Figur figur : spieler.getFigurenListe()) {
				// es wird geprueft ob eine Figur im Paradies steht
				if (figur.getPosition() == spielbrett.length - 1) {
					// wenn ja wird es der Liste hinzugefuegt
					gewonnen.add(figur.getFarbe());
				}
				// hier wird geprueft ob die laenge von den Figuren im Paradies
				// genau so lang ist wie die anzahl aller Figuren
				if (gewonnen.size() == spieler.getFigurenListe().length) {
					// wenn das zutrifft wird der Gewinner gesetzt
					setGewinner(spieler.getFarbe());
				}
			}
			// wenn der Spieler nicht gewonnen hat wird die Liste neu erstellt
			// damit im darauffolgenden durchlauf wieder geprueft werden kann
			gewonnen = new ArrayList<>();
		}
		return gewinner;
	}

	// set-Methode um den Gewinner zuzuweisen
	public void setGewinner(Farbe gewinner) {

		this.gewinner = gewinner;

	}

	// alle Spielerfarben werden zurueckgegeben als Array
	@Override
	public Farbe[] getAlleSpieler() {
		// TODO Auto-generated method stub
		return this.spielerFarben;
	}

}
