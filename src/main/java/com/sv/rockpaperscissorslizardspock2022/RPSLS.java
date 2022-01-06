/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.sv.rockpaperscissorslizardspock2022;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author: Steven Vallarsa
 *   email: stevenvallarsa@gmail.com
 *    date: 2022-01-06
 * purpose: Rock Paper Scissors Lizard Spack version of RPS
 */
public class RPSLS {

    public static void main(String[] args) {
        
        System.out.println("Welcome to Rock Paper Scissors Lizard Spock");
        System.out.println("-------------------------------------------");
        System.out.println("A new TWIST on an old CLASSIC/n/n");
        System.out.println("You can type in the whole word, the first letter or two,");
        System.out.println("(R)ock - (P)aper - (SC)issors - (L)izzard - (SP)ock");
        System.out.println("or use the ZXCVB keys in that order./n");
        System.out.println("Type (E)xit to quit, or (I)nfo for rules/n/n");
        
        boolean keepPlaying = true;
        
        while (keepPlaying) {
            String input = getInput();
            System.out.println("You inputted: " + input);
            
            if (input.equals("E")) {
                System.out.println("Thanks for playing!");
                keepPlaying = false;
            }
        }
        
        System.out.println("Goodbye.");
        
    }
    // start game
    // get user input
    // generate computer input
    // compare inputs
    // update leaderboard
    // display results
    // continue play?
    
    private static String getInput() {
        
        Scanner scanner = new Scanner(System.in);
        
        List<String> validInput = new ArrayList<>() {
            {
                add("R"); // (R)ock
                add("Z");
                
                add("P"); // (P)aper
                add("X");
                
                add("S"); // (SC)issors & (SP)ock
                add("C"); // Scissors
                add("B"); // Spock
                
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
                
                if (input.isEmpty() || input.isBlank()) {
                    throw new Exception("You didn't input anything. Please try again.\n");
                }
                
                String firstLetter = input.substring(0, 1);
                
                String secondLetter = "";
                if (input.length() > 1) {
                    secondLetter = input.substring(1, 2);
                }
                
                if (!validInput.contains(firstLetter)) {
                    throw new Exception("That's not a valid input. Please try again.\n");
                } 
                    
                return firstLetter;
                
                
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
}
