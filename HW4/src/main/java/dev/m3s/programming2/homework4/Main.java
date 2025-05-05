package dev.m3s.programming2.homework4;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            
            WordList words = new WordList("words.txt");
            
            boolean userPlayAgain = true;

            while (userPlayAgain) {
                Hangman game = new Hangman(words, 5);

                boolean gameEnded = game.theEnd();
                while (!gameEnded){
                    System.out.println("\nThe hidden word...");
                    System.out.println(game.getWordMask());
                    System.out.println("\nGuesses left: " + game.guessesLeft());
                    System.out.println("Guessed letters: " + game.guesses());
                    System.out.print("Guess a letter: ");
                    String input = sc.nextLine();
                    boolean ok = true;
                    if (input.length() != 1) {
                        ok = false;
                    } else {
                        char c = input.charAt(0);
                        if (!Character.isLetter(c)) {
                            ok = false;
                        }
                    }
                    
                    if (!ok) {
                        System.out.println("Please enter a valid letter.");
                        continue;
                    }

                    char guessedLetter = input.charAt(0);
                    boolean guessValid = game.guess(guessedLetter);
                    
                    if (!guessValid) {
                        System.out.println("Wrong guess.");
                    } else {
                        System.out.println("Good guess!");
                    }
                    
                    gameEnded = game.theEnd();
                }

                System.out.println("\nThe hidden word...");
                System.out.println(game.getWordMask());
                
                if (game.isWin()) {
                    System.out.println("Congratulations! You won!!!");
                } else {
                    System.out.println("Sorry, you lost!");
                }
                
                System.out.println("The hidden word was: \"" + game.word() + "\"");

                System.out.print("\nDo you want to play again? (y/n): ");
                String newGame = sc.nextLine();
                String lowerCaseResponse = newGame.toLowerCase();
                
                if (lowerCaseResponse.equals("y")) {
                    userPlayAgain = true;
                } else {
                    userPlayAgain = false;
                }
            }
            sc.close();
            System.out.println("Thank you for playing!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
