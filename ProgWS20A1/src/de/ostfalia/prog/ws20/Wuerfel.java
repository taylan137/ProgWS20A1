package de.ostfalia.prog.ws20;

import de.ostfalia.prog.ws20.enums.Farbe;

public class Wuerfel {
	Farbe[] farbenArray;

	// normaler Wuerfel
	public static int wuerfeln() {
		return (int) (Math.random() * 6 + 1);
	}
	
	public static int wuerfeln2() {
		return (int) ((Math.random() * 6 + 1) + (Math.random() * 6 + 1));
	}

	// hier wird die Farbe gewuerfelt(Wuerfel hat genau so viele Seiten wie Spieler
	// Anzahl)
	public Farbe farbeWuerfeln(int anzahlSpieler) {

		farbenArray = new Farbe[anzahlSpieler];
		int i = 0;
		for (Farbe farbe : Farbe.values()) {
  
			farbenArray[i] = farbe;
			i++;
		}
 
		return farbenArray[((int) (Math.random() * anzahlSpieler + 1))];
	}
}
