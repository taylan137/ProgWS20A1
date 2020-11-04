//package de.ostfalia.prog.ws20;
//
//import java.util.ArrayList;
//
//import de.ostfalia.prog.ws20.enums.Buchstabe;
//import de.ostfalia.prog.ws20.enums.Farbe;
//
//public class Methoden {
//
//	// eigene Sammlung von Funktionen
//
//	public static String[] stringZuArray(String conf) {
//		String editedConf = conf.replaceAll(" ", "");
//		editedConf = editedConf.replaceAll("-", ":");
//		editedConf = editedConf.replaceAll(",", ":");
//		String[] confArray = editedConf.split(":");
//		return confArray;
//	}  
// 
//	public static String[] farbenSotieren(String[] conf, Farbe... farben) {
//
//		ArrayList<String> confList = new ArrayList<String>();
//		int anzahlFarben = farben.length;
//		for (int i = 0; i < anzahlFarben; i++) {
//
//			for (int index = 0; index < conf.length; index += 3) {
//				if (farben[i].toString().equals(conf[index])) {
//					confList.add(farben[i].toString());
//					confList.add(conf[index + 1].toString());
//					confList.add(conf[index + 2].toString());
//				}
//			}
//		}
//		String[] sortierterConf = new String[conf.length];
//		int i = 0;
//
//		for (String string : confList) {
//			sortierterConf[i++] = string;
//		}
//		
//		return sortierterConf;
//	}
//
//	public static Farbe stringZuFarbe(String stringFarbe) {
//		for(Farbe farbe : Farbe.values()) {
//			if(farbe.toString().equals(stringFarbe)) {
//				return farbe; 
//			}
//		}
//		return null;
//	}
//	public static Buchstabe stringZuBuchstabe(String stringBuchstabe) {
//		for(Buchstabe buchstabe : Buchstabe.values()) {
//			if(buchstabe.toString().equals(stringBuchstabe)) {
//				return buchstabe;
//			}
//		}
//		return null;
//	}
//	
//	
//	public static Figur[] leerenArrayPlatzFinden(Figur[] figurenListe, Figur figur) {
//		for(int i = 0; i < figurenListe.length; i++) {
//			if(figurenListe[i] != null) {
//				figurenListe[i] = figur;
//				return figurenListe;
//			}
//		}
//		return figurenListe;
//	}
//}
//
