package de.ostfalia.prog.ws20;

import de.ostfalia.prog.ws20.enums.Buchstabe;
import de.ostfalia.prog.ws20.enums.Farbe;

public class Funktion {
//	klasse mit funktionen die �fters Verwendet werden
//	in einer extra Klasse f�r mehr �bersicht
 
	// hier wird der String zu einem Array aufgeteilt
	// "BLAU-A-30" wird zu (BLAU, A, 30)
	public static String[] stringZuArray(String conf) {
		String editedConf = conf.replaceAll(" ", "");
		editedConf = editedConf.replaceAll("-", ":");
		editedConf = editedConf.replaceAll(",", ":");
		String[] confArray = editedConf.split(":");
		return confArray;
	} 

	// hier wird der String zu Farbe(enum)
	public static Farbe stringZuFarbe(String stringFarbe) {
		for (Farbe farbe : Farbe.values()) {
			if (farbe.toString().equals(stringFarbe)) {
				return farbe;
			}
		}
		return null;
	}

	// hier wird der String zu Buchstabe(enum)
	public static Buchstabe stringZuBuchstabe(String stringBuchstabe) {
		for (Buchstabe buchstabe : Buchstabe.values()) {
			if (buchstabe.toString().equals(stringBuchstabe)) {
				return buchstabe;
			}
		}
		return null;
	}

	// hier werden die Augenzahlen addiert
	public static int augenzahlenAddieren(int... augenzahlen) {
		int ergebnis = 0;
		for (int addition : augenzahlen) {
			ergebnis += addition;
		}
		return ergebnis;
	}

	// hier wird die Position ausgerechnet
	public static int positionRechner(int spielfeldGroesse, int pos, int augenzahlen) {
		// hier wird geguckt ob die Position au�erhalb der Spielfelds ist
		if (pos > spielfeldGroesse) {
			return -1;
		}
		// hier wird ueberprueft ob die Figur schon im Ziel ist
		if (!(pos == spielfeldGroesse)) {
			int neuePos = pos + augenzahlen;

			if (neuePos > spielfeldGroesse) {
				neuePos = spielfeldGroesse - (neuePos - spielfeldGroesse);
			}
			// neue Position wird zurueckgegeben
			return neuePos;

		}
		// ansonsten alte Position zurueckgebenS
		return pos;
	}

}
