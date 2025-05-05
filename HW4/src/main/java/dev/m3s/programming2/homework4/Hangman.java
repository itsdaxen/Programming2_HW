package dev.m3s.programming2.homework4;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Hangman {

    private String word;
    private int guessesLeft;
    private List<Character> guessedLetters = new ArrayList<>();
    private char[] wordMask;

    public Hangman(WordList wordList, int maxAttempts) {
        List<String> allWords = wordList.giveWords();
        Random rand = new Random();
        int randIndex = rand.nextInt(allWords.size());
        this.word = allWords.get(randIndex).toLowerCase();
        this.guessesLeft = maxAttempts;

        this.wordMask = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            this.wordMask[i] = '*';
        }
    }

    public boolean guess(Character c) {
        char lowercaseChar = Character.toLowerCase(c);
        
        if (!guessedLetters.contains(lowercaseChar)) {
            guessedLetters.add(lowercaseChar);
            
            boolean foundLetter = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == lowercaseChar) {
                    wordMask[i] = lowercaseChar;
                    foundLetter = true;
                }
            }
            
            if (!foundLetter && guessesLeft > 0) {
                guessesLeft = guessesLeft - 1;
            }
            
            return foundLetter;
        }
        
        boolean letterInWord = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == lowercaseChar) {
                letterInWord = true;
                break;
            }
        }
        return letterInWord;
    }

    public List<Character> guesses() {
        List<Character> copyGuess = new ArrayList<Character>();
        for (int i = 0; i < guessedLetters.size(); i++) {
            Character letter = guessedLetters.get(i);
            copyGuess.add(letter);
        }
        return copyGuess;
    }

    public int guessesLeft() {
        return guessesLeft;
    }

    public String getWordMask() {
        String maskAsString = "";
        for (int i = 0; i < wordMask.length; i++) {
            maskAsString = maskAsString + wordMask[i];
        }
        return maskAsString;
    }

    public boolean theEnd() {
        if (guessesLeft == 0) {
            return true;
        }
        
        String currentMask = getWordMask();
        if (currentMask.equals(word)) {
            return true;
        }
        
        return false;
    }

    public boolean isWin() {
        String currentMask = getWordMask();
        return currentMask.equals(word);
    }

    public String word() {
        return word;
    }
}
