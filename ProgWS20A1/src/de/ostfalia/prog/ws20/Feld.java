package de.ostfalia.prog.ws20;

import java.util.ArrayList;

public class Feld {

	private ArrayList<Figur> figurenAufDemFeld = new ArrayList<>();
	int position;

	public Feld(int position) {
		this.position = position;
	}

	public void setzeFigur(Figur figur) {
		figurenAufDemFeld.add(figur);
	}

	public boolean entferneFigur(Figur figur) {
		return sucheFigur(figur) ? true : false;
	}

	public boolean sucheFigur(Figur figur) {
		for (Figur figurImArray : figurenAufDemFeld) {
			if (figurImArray.equals(figur)) {
				return true;
			}
		}
		return false;
	}
	
	public void bewegeFigur() {
		
	}

}
