/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.rockpaperscissorslizardspock2022;

import java.awt.print.Paper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author: Steven Vallarsa
 *   email: stevenvallarsa@gmail.com
 *    date: 2022-01-06
 * purpose: Rock Paper Scissors Lizard Spack version of RPS
 */
public class RPSLS {

    public static void main(String[] args) {
        
        int playerWins = 0;
        int computerWins = 0;
        int gamesTotal = 0;
        
        // START GAME BY DISPLAYING INFO
        displayInfo();
        
        // PLAY GAME
        while (true) {
            
            String userPlay = getUserInput();
            
            if (userPlay.equals("E")) {
                // CHECK FOR EXIT
                break;
            } else if (userPlay.equals("I")) {
                // CHECK FOR INFO
                displayInfo();
            } else {
                // PLAY ROUND
                String computerPlay = getComputerInput();
                gamesTotal++;
                
                if (userPlay.equals(computerPlay)) {
                    System.out.println("It's a tie! You both picked " + userPlay + "!");

                } else {
                    
                    String result = getResults(userPlay, computerPlay);
                    

                    if (result.substring(0, 1).equals("V")) {
                        playerWins++;
                    } else {
                        computerWins++;
                    } 

                    System.out.println(result);

                }
            }

        }
        
        System.out.println("Thanks for playing!\n");
        System.out.println("FINAL STATS");
        System.out.println("You played " + gamesTotal + " games with:");
        System.out.println("- wins: " + playerWins);
        System.out.println("- losses: " + computerWins);
        System.out.println("- draws: " + (gamesTotal - computerWins - playerWins));
        System.out.println("");
        System.out.println("Goodbye.");
        
    }

    
    private static String getUserInput() {
        
        Scanner scanner = new Scanner(System.in);
        
        List<String> validInput = new ArrayList<>() {
            {
                add("R"); // (R)ock
                add("Z");
                
                add("P"); // (P)aper
                add("X");
                
                add("S"); // (SC)issors or (SP)ock
                add("C"); // Scissors
                add("B"); // Spock
                add("K"); // Spoc(K)
                
                add("L"); // (L)izard
                add("V");
                
                add("E"); // (E)xit
                add("I"); // (I)nfo
            }
        };
        
        /**
         * VALIDATE USER INPUT
         * if input is blank, throw exception
         * if input isn't one of the six possible choices, throw exception
         * if input is "S" and second character is not "C" or "P", throw exception
         * if all passes, return Character(s) to determine round winner
         */
                
        while(true) {
            
            System.out.println("What is your choice?");
            String input = scanner.nextLine().toUpperCase();
            
            try {
                
                // IS INPUT BLANK?
                if (input.isEmpty() || input.isBlank()) {
                    throw new Exception("You didn't input anything. Please try again.\n");
                }
                
                String firstLetter = input.substring(0, 1);
                
                String secondLetter = "";
                if (input.length() > 1) {
                    secondLetter = input.substring(1, 2);
                }
                
                // IS FIRST LETTER VALID?
                if (!validInput.contains(firstLetter)) {
                    throw new Exception("That's not a valid input. Please try again.\n");
                // IS FIRST LETTER AN "S"?
                } else if (firstLetter.equals("S")) {
                    // IS SECOND LETTER "C" or "P"?
                    if (!((secondLetter.equals("C") || 
                            secondLetter.equals("P")))) {
                        
                        throw new Exception("Did you mean (SC)issors or (SP)ock?");
                    }
                    firstLetter += secondLetter;
                }
                    switch(firstLetter) {
                        case "E":
                            return "E";
                        case "I":
                            return "I";
                        case "R":
                        case "Z":
                            return String.valueOf(Plays.ROCK);
                        case "P":
                        case "X":
                            return String.valueOf(Plays.PAPER);
                        case "SC":
                        case "C":
                            return String.valueOf(Plays.SCISSORS);
                        case "L":
                        case "V":
                            return String.valueOf(Plays.LIZARD);
                        case "SP":
                        case "K":
                        case "B":
                            return String.valueOf(Plays.SPOCK);
                    }
                    return null;
                
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    private static void displayInfo() {
        System.out.println("Welcome to Rock Paper Scissors Lizard Spock");
        System.out.println("-------------------------------------------");
        System.out.println("A new TWIST on an old CLASSIC\n");
        System.out.println("You can type in the whole word, the first letter or two,");
        System.out.println("(R)ock - (P)aper - (SC)issors - (L)izzard - (SP)oc(K)");
        System.out.println("or use the ZXCVB keys in that order.\n");
        System.out.println("Type (E)xit to quit, or (I)nfo for rules\n");
    }
    
    private static String getComputerInput() {
        Random random = new Random();
        int computersChoice = random.nextInt(5);
        
        switch (computersChoice) {
            case 0:
                return String.valueOf(Plays.ROCK);
            case 1:
                return String.valueOf(Plays.PAPER);
            case 2:
                return String.valueOf(Plays.SCISSORS);
            case 3:
                return String.valueOf(Plays.LIZARD);
            case 4:
                return String.valueOf(Plays.SPOCK);
        }
        return null;
    }
    
    private static String getResults(String userPlay, String computerPlay) {
        
        /**
         * R beats SC & L
         * P beats R & SP
         * SC beats P & L
         * L beats SP & P
         * SP beats R & SC
         */
        
        // USER WINS
        if (userPlay.equals(String.valueOf(Plays.ROCK)) && 
                computerPlay.equals(String.valueOf(Plays.SCISSORS))) {
            return "Victory! ROCK breaks SCISSORS";
        }
        
        if (userPlay.equals(String.valueOf(Plays.ROCK)) && 
                computerPlay.equals(String.valueOf(Plays.LIZARD))) {
            return "Victory! ROCK crushes LIZARD";
        }
        
        if (userPlay.equals(String.valueOf(Plays.PAPER)) && 
                computerPlay.equals(String.valueOf(Plays.ROCK))) {
            return "Victory! PAPER covers ROCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.PAPER)) && 
                computerPlay.equals(String.valueOf(Plays.SPOCK))) {
            return "Victory! PAPER disproves SPOCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SCISSORS)) && 
                computerPlay.equals(String.valueOf(Plays.PAPER))) {
            return "Victory! SCISSORS cut PAPER";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SCISSORS)) && 
                computerPlay.equals(String.valueOf(Plays.LIZARD))) {
            return "Victory! SCISSORS decapitates LIZARD";
        }
        
        if (userPlay.equals(String.valueOf(Plays.LIZARD)) && 
                computerPlay.equals(String.valueOf(Plays.PAPER))) {
            return "Victory! LIZARD eats PAPER";
        }
        
        if (userPlay.equals(String.valueOf(Plays.LIZARD)) && 
                computerPlay.equals(String.valueOf(Plays.SPOCK))) {
            return "Victory! LIZARD poisons SPOCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SPOCK)) && 
                computerPlay.equals(String.valueOf(Plays.SCISSORS))) {
            return "Victory! SPOCK mindmelds SCISSORS";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SPOCK)) && 
                computerPlay.equals(String.valueOf(Plays.ROCK))) {
            return "Victory! SPOCK nervepinches ROCK";
        }
                
        
        // COMPUTER WINS
        if (userPlay.equals(String.valueOf(Plays.SCISSORS)) && 
                computerPlay.equals(String.valueOf(Plays.ROCK))) {
            return "Defeat! ROCK breaks SCISSORS";
        }
        
        if (userPlay.equals(String.valueOf(Plays.LIZARD)) && 
                computerPlay.equals(String.valueOf(Plays.ROCK))) {
            return "Defeat! ROCK crushes LIZARD";
        }
        
        if (userPlay.equals(String.valueOf(Plays.ROCK)) && 
                computerPlay.equals(String.valueOf(Plays.PAPER))) {
            return "Defeat! PAPER covers ROCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SPOCK)) && 
                computerPlay.equals(String.valueOf(Plays.PAPER))) {
            return "Defeat! PAPER disproves SPOCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.PAPER)) && 
                computerPlay.equals(String.valueOf(Plays.SCISSORS))) {
            return "Defeat! SCISSORS cut PAPER";
        }
        
        if (userPlay.equals(String.valueOf(Plays.LIZARD)) && 
                computerPlay.equals(String.valueOf(Plays.SCISSORS))) {
            return "Defeat! SCISSORS decapitates LIZARD";
        }
        
        if (userPlay.equals(String.valueOf(Plays.PAPER)) && 
                computerPlay.equals(String.valueOf(Plays.LIZARD))) {
            return "Defeat! LIZARD eats PAPER";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SPOCK)) && 
                computerPlay.equals(String.valueOf(Plays.LIZARD))) {
            return "Defeat! LIZARD poisons SPOCK";
        }
        
        if (userPlay.equals(String.valueOf(Plays.SCISSORS)) && 
                computerPlay.equals(String.valueOf(Plays.SPOCK))) {
            return "Defeat! SPOCK mindmelds SCISSORS";
        }
        
        if (userPlay.equals(String.valueOf(Plays.ROCK)) && 
                computerPlay.equals(String.valueOf(Plays.SPOCK))) {
            return "Defeat! SPOCK nervepinches ROCK";
        }
                
        return "Oops. Since you're seeing this message something messed up.";
    }
    
    private enum Plays {
        ROCK,
        PAPER,
        SCISSORS,
        LIZARD,
        SPOCK
        
    }
}
