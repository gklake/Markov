/*
* Title: Markov.java
* Abstract: This code will read in words from a text file, randomly select words from the file to generate new text
* Author: Gabrielle Lake
* Date: 2/18/2021
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Markov {

    static final String PUNCTUATION = "__$";
    static final String PUNCTUATION_MARKS = ".!?";

    private HashMap<String, ArrayList<String>> words;
    private String prevWord;

    public Markov() {
        //HashMap<String, ArrayList<String>> words = new HashMap<>();
        words = new HashMap<>();
        ArrayList<String> values = new ArrayList<>();
        words.put(PUNCTUATION, values);
        prevWord = PUNCTUATION;
    }

    HashMap<String, ArrayList<String>> getWords() {
        return words;
    }

    public void addFromFile(String filename) {
        Scanner scan = null;
        File f = new File(filename);
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("could not find the file" + e);
        }
        while(scan != null && scan.hasNextLine()) {
            addLine(scan.nextLine());
        }
    }

    void addLine(String readLine) {
        if(readLine.length() <= 0) {
            return;
        }
        for(String w : readLine.split(" ")) {
            if(w.length() > 0){
                addWord(w);
            }
        }
    }

    void addWord(String addedWord) {
        if(endsWithPunctuation(prevWord)) {
            words.get(PUNCTUATION).add(addedWord);
        } else {
            if(!words.containsKey(prevWord)) {
                words.put(prevWord, new ArrayList<>());
            }
            words.get(prevWord).add(addedWord);
        }
        prevWord = addedWord;
    }

    public String getSentence() {
        StringBuilder sentence = new StringBuilder();
        String currentWord = randomWord(PUNCTUATION);

        while (!endsWithPunctuation(currentWord)) {
            sentence.append(currentWord);
            sentence.append(" ");
            currentWord = randomWord(currentWord);
        }
        sentence.append(currentWord);
        return sentence.toString();
    }

    String randomWord(String chosenKey) {
        Random randomIndex = new Random();
        int upperBound = words.get(chosenKey).size();
        return words.get(chosenKey).get(randomIndex.nextInt(upperBound));
    }

    public boolean endsWithPunctuation(String checkedWord){
        String checkedPunctuation = checkedWord.substring(checkedWord.length()-1);
        return PUNCTUATION_MARKS.contains(checkedPunctuation);
    }

    @Override
    public String toString() {
        return "" + words + "";
    }
}
