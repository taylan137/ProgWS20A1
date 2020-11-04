package de.ostfalia.prog.ws20.test;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import de.ostfalia.prog.ws20.Paradiesspiel;
import de.ostfalia.prog.ws20.enums.Farbe;
import de.ostfalia.prog.ws20.interfaces.IParadiesspiel;

/**
 * Testfälle für Aufgabe 2
 * 
 * @author D. Dick
 * @since WS 2020/2021
 *
 */
public class Aufgabe2Test {
	
	@Rule
	public TestRule timeout = new DisableOnDebug(new Timeout(500, TimeUnit.MILLISECONDS));
	
	@Before
	public void before() {}
	
	@After
	public void after() {}

	/*
	 * ***************************************************************************
	 * Testfälle für "Glück"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Die Figuren landen jeweils 1x auf ein "Glück"-Feld und
	 * dürfen sich noch einmal bewegen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGlueck1x() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:12, GELB-B:15, BLAU-B:28", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-A", 1, 1);
		assertEquals("Die Figur GELB-A muss auf Feld Nr. 16 sein.", 16, spiel.getFigurposition("GELB-A"));

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 39 sein.", 39, spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur bewegt sich und, weil sie auf ein "Glück"-Feld
	 * landet, bewegt sie sich erneut. Dabei landet sie erneut auf ein "Glück"-Feld
	 * und bewegen sich erneut bis die Kettenreaktion vollzogen ist.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGlueckKettenreaktion1() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:15, GELB-B:21, BLAU-B:28", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-B", 2, 2);
		assertEquals("Die Figur BLAU-B muss auf Feld Nr. 40 sein (Kettenreaktion: 28 -> 32 -> 40).", 40,
				spiel.getFigurposition("BLAU-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur bewegt sich und, weil sie auf ein "Glück"-Feld
	 * landet, bewegt sie sich erneut. Dabei landet sie erneut auf ein "Glück"-Feld
	 * und bewegen sich erneut bis die Kettenreaktion vollzogen ist.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGlueckKettenreaktion2() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:10, GELB-B:21, BLAU-B:28", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-A", 2, 2);
		assertEquals("Die Figur GELB-A muss auf Feld Nr. 22 sein (Kettenreaktion: 10 -> 14 -> 18 -> 22).", 22,
				spiel.getFigurposition("GELB-A"));
	}

	/*
	 * ***************************************************************************
	 * Testfälle für "Brücke"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden. Eine
	 * Figur wird bewegt und landet dabei auf ein "Brücke"-Feld und wird daher weiterbefördert.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBrueckeZiel() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB, Farbe.GRUEN);

		spiel.setFarbeAmZug(Farbe.GRUEN);
		spiel.bewegeFigur("GRUEN-A", 4, 2);
		assertEquals("Die Figur GRUEN-A muss auf Feld Nr. 12 sein.", 12, spiel.getFigurposition("GRUEN-A"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und kommt an einem "Brücke"-Feld
	 * vorbei. Die Weiterbewegung findet dann vom Ziel der Brücke normal weiter
	 * statt.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBrueckeNichtZiel() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:4, GELB-B:4, BLAU-B:7", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 22 sein (Weg: 4 -> 5 -> 6/12 -> 13 -> 14 -> ... -> 22).",
				22, spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden. Eine
	 * Figur wird bewegt, kommt an einem "Brücke"-Feld vorbei und landet schließlich
	 * auf ein "Glück"-Feld.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBrueckeMitGlueck() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 2);
		assertEquals(
				"Die Figur GELB-B muss auf Feld Nr. 30 sein (Kettenreaktion: 0 -> 1 -> ... -> 6/12 -> 13 -> 14 -> "
						+ "15 -> ... -> 22).",
				22, spiel.getFigurposition("GELB-B"));
	}

	/*
	 * ***************************************************************************
	 * Testfälle für "Aufschwung"-Feld
	 * ***************************************************************************
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und geht dabei auf ein
	 * "Aufschwung"-Feld vorbei. Es wird keine besondere Reaktion erwartet.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAufschwungVorbei() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:51, GELB-B:46, BLAU-B:46", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-A", 1, 1);
		assertEquals("Die Figur GELB-A muss auf Feld Nr. 53 sein.", 53, spiel.getFigurposition("GELB-A"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Aufschwung"-Feld. Da sie mit 2 6-er das Feld erreichte, wird sie direkt zum Paradies befördert.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAufschwungMit12InsParadies() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:51, GELB-B:40, BLAU-B:46", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 63 sein (Weg: 40 -> 41 -> ... -> 52 -> 63).", 63,
				spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und landet dabei auf ein
	 * "Aufschwung"-Feld. Da sie nicht mit 2 6-er das Feld erreichte, bleibt sie dort stehen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAufschwungMit7NichtInsParadies() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:51, GELB-B:45, BLAU-B:46", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 1, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 52 sein.", 52, spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf dem
	 * Spielfeld befinden. Eine Figur wird bewegt und kommt bis zum Paradies, muss aber zurückgehen. Dabei landet sie auf ein
	 * "Aufschwung"-Feld und wird direkt zum Paradies befördert, da sie mit 2 6-er das Feld erreichte.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAufschwungVon62InsParadies() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel("GELB-A:51, GELB-B:62, BLAU-B:46", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 63 sein (Weg: 62 -> 63 -> 62 -> ... -> 52 -> 63).", 63,
				spiel.getFigurposition("GELB-B"));
	}

}
