package de.ostfalia.prog.ws20;

import java.util.ArrayList;

import de.ostfalia.prog.ws20.enums.Farbe;
import de.ostfalia.prog.ws20.enums.Buchstabe;

public class Spieler {

	// Attribute von dere Klasse Spieler
	private Farbe farbe;
	private ArrayList<Figur> figurenListe = new ArrayList<>();
	private int anzahlFiguren;

	// Konstruktor erstellt Figuren die in die Liste hinzugef�gt werden vom Spieler
	public Spieler(Farbe farbe) {
		anzahlFiguren = 2;
		this.farbe = farbe;
  
		for (int i = 0; i < anzahlFiguren; i++) {
			Figur figur = new Figur(farbe, Buchstabe.values()[i]);
			figurenListe.add(figur);
		}
	}

	// getter/setter Methoden
	public Farbe getFarbe() {
		return farbe;
	}

	public void setFarbe(Farbe farbe) {
		this.farbe = farbe;
	}

	public int getAnzahlFiguren() {
		return anzahlFiguren;
	}

	public void setAnzahlFiguren(int anzahlFiguren) {
		this.anzahlFiguren = anzahlFiguren;
	}

	// Liste wird als Array zureckgegeben
	public Figur[] getFigurenListe() {
		Figur[] array = new Figur[figurenListe.size()];
		int idx = 0;
		for (Figur figur : figurenListe) {
			array[idx++] = figur;
		}
		return array;
	}

}
