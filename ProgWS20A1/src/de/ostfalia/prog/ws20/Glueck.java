package de.ostfalia.prog.ws20;

import java.util.ArrayList;
import java.util.Arrays;

public class Glueck extends Feld{
	
	ArrayList<Integer> pos = new ArrayList<Integer>(Arrays.asList(new Integer[]{14, 18, 27, 32, 36, 50}));
	
	
	
	
	
	public boolean pruefe(int i) {
		
		return pos.contains(i) ? true : false;
		
	}

}
