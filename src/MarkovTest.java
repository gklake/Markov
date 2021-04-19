/*
 * Title: MarkovTest.java
 * Abstract: This code will test Markov.java
 * Author: Gabrielle Lake
 * Date: 2/27/2021
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class MarkovTest {

    static final String PUNCTUATION = "__$";
    static final String PUNCTUATION_MARKS = ".!?";

    private HashMap<String, ArrayList<String>> words;
    private String prevWord;

    @BeforeEach
    void setUp() {
        System.out.println("Processing Setup");
        words = new HashMap<>();
        prevWord = PUNCTUATION;
    }

    @AfterEach
    void tearDown() {
        System.out.println("Processing Teardown");
        prevWord = null;
        words.clear();
    }

    @Test
    void Markov(){
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        prevWord = PUNCTUATION;
        assertNotNull(words);
        assertTrue(words.containsKey(PUNCTUATION));
    }

    @Test
    void getWords() {
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        words.get(PUNCTUATION).add("Hello");
        words.put("Hello", new ArrayList<>());
        words.get("Hello").add("World.");
        Markov markov1 = new Markov();
        markov1.addFromFile("testFile.txt");

        assertEquals(words, markov1.getWords());
    }


    @Test
    void getSentence() {
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        words.get(PUNCTUATION).add("Hello");
        words.put("Hello", new ArrayList<>());
        words.get("Hello").add("World.");
        Markov markov2 = new Markov();
        markov2.addFromFile("testFile.txt");

        assertEquals("Hello World.", markov2.getSentence());

    }

    @Test
    void randomWord() {
        Markov markov3 = new Markov();
        //Markov markov3_5 = new Markov();
        markov3.addFromFile("phrases.txt");
        //markov3_5.addFromFile("phrases.txt");

        assertNotEquals(markov3.randomWord(PUNCTUATION), markov3.randomWord(PUNCTUATION));
    }

    @Test
    void endsWithPunctuation() {
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        words.get(PUNCTUATION).add("Hello");
        words.put("Hello", new ArrayList<>());
        words.get("Hello").add("World.");
        Markov markov4 = new Markov();
        markov4.addFromFile("testFile.txt");

        assertTrue(markov4.endsWithPunctuation(words.get("Hello").get(0)));
        assertFalse(markov4.endsWithPunctuation(words.get(PUNCTUATION).get(0)));
    }

    @Test
    void testToString() {
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        words.get(PUNCTUATION).add("Hello");
        words.put("Hello", new ArrayList<>());
        words.get("Hello").add("World.");
        Markov markov5 = new Markov();
        markov5.addFromFile("testFile.txt");

        assertEquals(markov5.toString().length(), words.toString().length());
    }
}