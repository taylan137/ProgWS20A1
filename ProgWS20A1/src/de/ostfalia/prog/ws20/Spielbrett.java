package de.ostfalia.prog.ws20;

import java.util.ArrayList;

//die Klasse wurde kaum verwendet
public class Spielbrett {

	// Spielbrett mit einer ArrayListe<Figur> um Figuren auf die einzelnen Felder zu
	// setzen
	private ArrayList<Figur> figurenAufDemFeld = new ArrayList<>();

	// Methode um eine Figur aufs Feld zu setzen
	public void setzeFigur(Figur figur) {
		figurenAufDemFeld.add(figur);
	}
  
	// Methode um Figur zu Loeschen vom Feld
	public void loescheFigur(Figur figur) {
		figurenAufDemFeld.remove(figur);
	}
 
	// getter Methode um die Figuren liste zurueckzugeben
	public ArrayList<Figur> getFigurenAufDemFeld() {
		return figurenAufDemFeld;
	}

	// Methode um eine figur aufs Feld zu setzen
	public void setzeFigure(Figur[] figure) {
		for (int i = 0; i < figure.length; i++) {
			setzeFigur(figure[i]);
		}
	}

}
