package de.ostfalia.prog.ws20;

import de.ostfalia.prog.ws20.enums.Buchstabe;
import de.ostfalia.prog.ws20.enums.Farbe;

public class Figur {

	// Standard Figur Klasse mit 3 Attributen, 2 Konstrukturen mit getter/setter
	// Methoden 

	private Farbe farbe;
	private Buchstabe buchstabe;
	private int position;

	public Figur(Farbe farbe, Buchstabe buchstabe) {
		this.farbe = farbe;
		this.buchstabe = buchstabe;
		this.position = 0;
	}

	public Figur(Farbe farbe, Buchstabe buchstabe, int position) {
		this.farbe = farbe;
		this.buchstabe = buchstabe;
		this.position = position;
	}

	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public Buchstabe getBuchstabe() {
		return buchstabe;
	}

	public void setBuchstabe(Buchstabe buchstabe) {
		this.buchstabe = buchstabe;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

}
