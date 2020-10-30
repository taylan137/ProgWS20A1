package de.ostfalia.prog.ws20.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
 * Testfälle für Aufgabe 1
 * 
 * @author D. Dick
 * @since WS 2020/2021
 *
 */
public class Aufgabe1Test {

	@Rule
	public TestRule timeout = new DisableOnDebug(
			new Timeout(500, TimeUnit.MILLISECONDS));

	@Before
	public void before() {
	}

	@After
	public void after() {
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für die Initialisierung des Spiels
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt zwei unterschiedlichen Instanzen der Klasse Paradiesspiel
	 * und überprüft, ob die Attributen ggf. 'static' deklariert sind.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testZweiInstanzen() throws Exception {
		IParadiesspiel spielA = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN, Farbe.ROT);
		IParadiesspiel spielB = new Paradiesspiel(Farbe.BLAU, Farbe.GELB);

		Farbe[] erwarteteFarbenA = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN,
				Farbe.ROT};
		assertArrayEquals(
				"Es sind nicht alle erwartete Farben im Spiel-A vorhanden",
				erwarteteFarbenA, spielA.getAlleSpieler());

		Farbe[] erwarteteFarbenB = {Farbe.BLAU, Farbe.GELB};
		assertArrayEquals(
				"Es sind nicht alle erwartete Farben im Spiel-B vorhanden",
				erwarteteFarbenB, spielB.getAlleSpieler());

		assertEquals("Die Figur GRUEN-A muss auf Position 0 sein.", 0,
				spielA.getFigurposition("GRUEN-A"));
		assertEquals("Die Figur ROT-B muss auf Position 0 sein.", 0,
				spielA.getFigurposition("ROT-B"));
	}

	/**
	 * Test erzeugt 6 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * müssen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErzeuge6SpielerFigurenStandard() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN, Farbe.ROT, Farbe.SCHWARZ, Farbe.WEISS);

		Farbe[] erwarteteFarben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN,
				Farbe.ROT, Farbe.SCHWARZ, Farbe.WEISS};
		assertArrayEquals(
				"Es sind nicht alle erwartete Farben im Spiel vorhanden",
				erwarteteFarben, spiel.getAlleSpieler());

		assertEquals("Die Figur SCHWARZ-A muss auf Position 0 sein.", 0,
				spiel.getFigurposition("SCHWARZ-A"));
		assertEquals("Die Figur SCHWARZ-B muss auf Position 0 sein.", 0,
				spiel.getFigurposition("SCHWARZ-B"));

		assertEquals("Die Figur WEISS-A muss auf Position 0 sein.", 0,
				spiel.getFigurposition("WEISS-A"));
		assertEquals("Die Figur WEISS-B muss auf Position 0 sein.", 0,
				spiel.getFigurposition("WEISS-B"));
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * müssen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testErzeuge3SpielerFigurenStandard() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		Farbe[] erwarteteFarben = {Farbe.BLAU, Farbe.GELB, Farbe.GRUEN};
		assertArrayEquals("Die erwarteten Farben im Spiel sind nicht korrekt",
				erwarteteFarben, spiel.getAlleSpieler());

		assertEquals("Die Figur GRUEN-A muss auf dem Startfeld sein.", 0,
				spiel.getFigurposition("GRUEN-A"));
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * müssen. Es wird im Test nach einer Figur gesucht, die nicht existiert
	 * (Farbe nicht vorhanden).
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFigurStartpositionStandardErrorFarbe() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		assertEquals(
				"Die Figur ROT-A ist nicht im Spiel und darf daher nicht gefunden werden.",
				-1, spiel.getFigurposition("ROT-A"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * müssen. Es wird im Test nach einer Figur gesucht, die nicht existiert
	 * (Name nicht korrekt).
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFigurStartpositionStandardErrorName() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB);

		assertEquals(
				"Die Figur BLAU-X existiert nicht und darf daher nicht gefunden werden.",
				-1, spiel.getFigurposition("BLAU-X"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich nicht auf dem Startfeld
	 * befinden sollen, sondern woanders auf dem Spielfeld.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFigurStartpositionKonfiguriert() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"GELB-A:30, GELB-B:37, BLAU-B:7", Farbe.BLAU, Farbe.GELB);

		assertEquals("Die Figur GELB-A muss auf Feld Nr. 30 sein.", 30,
				spiel.getFigurposition("GELB-A"));
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 37 sein.", 37,
				spiel.getFigurposition("GELB-B"));
		assertEquals("Die Figur BLAU-A muss auf Feld Nr. 0 sein.", 0,
				spiel.getFigurposition("BLAU-A"));
		assertEquals("Die Figur BLAU-B muss auf Feld Nr. 7 sein.", 7,
				spiel.getFigurposition("BLAU-B"));
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für die Bewegung der Figur
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden
	 * und von dort aus bewegt werden. Alle gewünschte Figuren exisitieren und
	 * können bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurExistiert() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		spiel.setFarbeAmZug(Farbe.GRUEN);
		spiel.bewegeFigur("GRUEN-A", 2, 2);
		assertEquals("Die Figur GRUEN-A muss auf Feld Nr. 4 sein.", 4,
				spiel.getFigurposition("GRUEN-A"));

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-A", 1, 3);
		assertEquals("Die Figur BLAU-A muss auf Feld Nr. 4 sein.", 4,
				spiel.getFigurposition("BLAU-A"));

		spiel.setFarbeAmZug(Farbe.GRUEN);
		spiel.bewegeFigur("GRUEN-B", 1, 2);
		assertEquals("Die Figur GRUEN-B muss auf Feld Nr. 3 sein.", 3,
				spiel.getFigurposition("GRUEN-B"));
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden.
	 * Die gewünschte Figur exisitiert nicht (Name nicht vorhanden) und kann
	 * daher nicht bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurNichtExistiertName() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		spiel.setFarbeAmZug(Farbe.GRUEN);
		assertFalse("Die Figur GRUEN-X existiert nicht.",
				spiel.bewegeFigur("GRUEN-X", 1, 4));
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden.
	 * Die gewünschte Figur gehört aber nicht zum Spieler, der den Zug hat, und
	 * kann daher nicht bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurNichtVomSpieler() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		spiel.setFarbeAmZug(Farbe.GELB);
		assertFalse(
				"Die Figur GRUEN-A gehört nicht zum Spieler GELB und kann nicht bewegt werden.",
				spiel.bewegeFigur("GRUEN-A", 1, 4));
	}

	/**
	 * Test erzeugt 3 Spieler, dessen Figuren sich auf dem Startfeld befinden.
	 * Die gewünschte Figur exisitiert nicht (Farbe nicht bekannt) und kann
	 * daher nicht bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurNichtExistiertFarbe() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(Farbe.BLAU, Farbe.GELB,
				Farbe.GRUEN);

		spiel.setFarbeAmZug(Farbe.GRUEN);
		assertFalse("Die Figur WEISS-A existiert nicht.",
				spiel.bewegeFigur("WEISS-A", 3, 4));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Alle Figuren, die bewegt werden sollten, können
	 * das Paradiesfeld erreichen und auch dort beleiben.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurParadiesBleiben() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"BLAU-A:60, BLAU-B:51, GELB-B:51", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-A", 2, 1);
		assertEquals("Die Figur BLAU-A muss auf Feld Nr. 63 (Paradies) sein.",
				63, spiel.getFigurposition("BLAU-A"));

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 6, 6);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 63 (Paradies) sein.",
				63, spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Alle Figuren, die bewegt werden sollten, können
	 * nicht im Paradiesfeld bleiben, und müssen die zuviel gewürfelte
	 * Augenzahlen zurücklaufen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurParadiesZurueck() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"BLAU-A:61, BLAU-B:51, GELB-B:62", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-A", 2, 3);
		assertEquals("Die Figur BLAU-A muss auf Feld Nr. 60 sein.", 60,
				spiel.getFigurposition("BLAU-A"));

		spiel.setFarbeAmZug(Farbe.GELB);
		spiel.bewegeFigur("GELB-B", 1, 2);
		assertEquals("Die Figur GELB-B muss auf Feld Nr. 61 sein.", 61,
				spiel.getFigurposition("GELB-B"));
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden. Es wird versucht eine Figur, die sich bereits im
	 * Paradies befinet, von dort aus zu bewegen.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurAusDemParadies() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"BLAU-A:63, BLAU-B:51, GELB-B:63", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		assertFalse("Die Figur BLAU-A darf das Paradies nicht verlassen.",
				spiel.bewegeFigur("BLAU-A", 1, 5));
		assertEquals(
				"Die Figur BLAU-A muss weiterhin auf Feld Nr. 63 (Paradies) sein.",
				63, spiel.getFigurposition("BLAU-A"));
	}

	/*
	 * *************************************************************************
	 * ** Testfälle für Gewinner
	 * *************************************************************************
	 * **
	 */

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden, wobei zwei Spieler je eine Figur im Paradies
	 * haben. Nachdem eine weitere Figur zum Paradies bewegt wurde, steht der
	 * Gewinner fest.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGewinner() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"BLAU-A:63, BLAU-B:61, GELB-B:63", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-B", 1, 1);
		assertEquals("Der Spieler BLAU hat gewonnen.", Farbe.BLAU,
				spiel.getGewinner());
	}

	/**
	 * Test erzeugt 2 Spieler, dessen Figuren sich zum Teil bereits verteilt auf
	 * dem Spielfeld befinden, wobei ein Spieler zwei Figuren im Paradies hat
	 * und somit der Gewinner ist. Keine weitere Figur kann bewegt werden.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testBewegeFigurNachEnde() throws Exception {
		IParadiesspiel spiel = new Paradiesspiel(
				"BLAU-A:63, BLAU-B:61, GELB-B:63", Farbe.BLAU, Farbe.GELB);

		spiel.setFarbeAmZug(Farbe.BLAU);
		spiel.bewegeFigur("BLAU-B", 1, 1);
		assertEquals("Der Spieler BLAU hat gewonnen.", Farbe.BLAU,
				spiel.getGewinner());

		spiel.setFarbeAmZug(Farbe.GELB);
		assertFalse(
				"Die Figur GELB-A darf sich nicht bewegen. Das Spiel ist aus, da ein Gewinner bereits gibt.",
				spiel.bewegeFigur("GELB-A", 1, 1));
	}

}
