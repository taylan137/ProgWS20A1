package de.ostfalia.prog.ws20;

import java.util.ArrayList;
import java.util.Arrays;

public class Bruecke extends Feld{
	
ArrayList<Integer> pos = new ArrayList<Integer>(Arrays.asList(new Integer[]{12}));
	
	
	
	
	
	public boolean pruefe(int i) {
		
		return pos.contains(i) ? true : false;
		
	}

}
