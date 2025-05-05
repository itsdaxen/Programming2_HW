package dev.m3s.programming2.homework4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class WordList {
    private List<String> wordList;

    public WordList() {
        this.wordList = new ArrayList<>();
    }

    public WordList(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            fileName = "words.txt";
        }
        this.wordList = readFile(fileName);
    }

    private List<String> readFile(String fileName) {
        java.io.File file = new java.io.File(fileName);
        if (!file.exists()) {
            String basePath = System.getProperty("user.dir");
            file = new java.io.File(basePath + "/src/main/java/dev/m3s/programming2/homework4/words.txt");
            fileName = file.getPath();
        }

        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                if (!line.trim().isEmpty()) {
                    words.add(line);
                }
            }
            reader.close();
            return words;
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String> giveWords() {
        return wordList;
    }

    private void setWordList(List<String> list) {
        this.wordList = list;
    }

    public WordList theWordsOfLength(int length) {
        List<String> matchingWords = new ArrayList<>();
        
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if (word.length() == length) {
                matchingWords.add(word);
            }
        }

        WordList matchingWordsList = new WordList();
        matchingWordsList.setWordList(matchingWords);
        return matchingWordsList;
    }

    public WordList theWordsWithCharacters(String soughtPattern) {
        int length = soughtPattern.length();
        List<String> matchingWords = new ArrayList<>();
        
        for (int i = 0; i < wordList.size(); i++) {
            String word = wordList.get(i);
            if (word.length() == length) {
                boolean isMatch = true;
                for (int j = 0; j < length; j++) {
                    char patternChar = soughtPattern.charAt(j);
                    char wordChar = word.charAt(j);
                    if (patternChar != '_' && wordChar != patternChar) {
                        isMatch = false;
                        break;
                    }
                }
                if (isMatch) {
                    matchingWords.add(word);
                }
            }
        }

        WordList matchingWordsList = new WordList();
        matchingWordsList.setWordList(matchingWords);
        return matchingWordsList;
    }
}
