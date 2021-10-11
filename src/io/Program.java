package io;

import model.*;
import model.exceptions.InvalidInputException;
import model.exceptions.InvalidOutputException;

import java.util.Scanner;

public class Program {

    private static final Scanner scanner = new Scanner(System.in);

    private static final String range = "Note range: G3 to A7\n"
            + "Fingering range: 0 (open string) to 4+ (extended pinky)\n"
            + "Strings: G, D, A, E\n"
            + "Position range: 1st position to 12th position\n";

    public void run() {
        System.out.print("\nWelcome to the Violin Notes program!"
                + "\nIn this program, when 3 out of the 4 (fingering, note, position, string) are given, "
                + "the remaining can be found! See the reference image for more details.\n\n"
                + "Important: This program assumes a reasonable range for violin-playable "
                + "notes, fingerings, strings, and positions. For your information:\n" + range
                + "Be aware that any input or output that is not contained within the above ranges "
                + "will automatically result in the program informing you that something is \"not valid\".\n");
        String continueLooping = "yes";
        Note note = null; Fingering fingering = null; char string = '\0'; int position = 0;
        String rawInput;
        String theUnknown = "none";
        int numQuestionMark = 0;
        while (!continueLooping.equals("no")) {
            System.out.print("\nFor each of the following questions, if you do not know, then please enter \"???\""
                    + "\n(Warning: You can only enter \"???\" once each time. In other words, there can only be 1 unknown.)"
                    + "\n\nWhat is the note? Use format like \"G#3\" for the lowest G# playable on a violin: ");
            rawInput = scanner.nextLine();
            if (rawInput.equals("???")) {
                theUnknown = "note";
                numQuestionMark++;
            } else {
                note = new Note(rawInput);
            }
            System.out.print("What is the fingering? Use format like \"4-\" for retracted 4th finger, "
                    + "or \"3\" for regular 3rd finger: ");
            rawInput = scanner.nextLine();
            if (rawInput.equals("???")) {
                theUnknown = "fingering";
                numQuestionMark++;
            } else if (rawInput.length() == 1) {
                fingering = new Fingering(Integer.parseInt(rawInput));
            } else {
                fingering = new Fingering(rawInput);
            }
            System.out.print("What is the string? Use format like \"G\" for G string: ");
            rawInput = scanner.nextLine();
            if (rawInput.equals("???")) {
                theUnknown = "string";
                numQuestionMark++;
            } else {
                string = rawInput.charAt(0);
            }
            System.out.print("What is the position? Use format like \"3\" for 3rd position: ");
            rawInput = scanner.nextLine();
            if (rawInput.equals("???")) {
                theUnknown = "position";
                numQuestionMark++;
            } else {
                position = Integer.parseInt(rawInput);
            }
            if (numQuestionMark > 1) {
                theUnknown = "too many";
            }
            try {
                switch (theUnknown) {
                    case "none":
                        System.out.print("\nYou have all the information you need. You don't need to use this program, lol!\n");
                        break;
                    case "note":
                        System.out.print("\nYour unknown is: NOTE");
                        note = Violin.findNote(string, position, fingering);
                        System.out.print("\nYour note is: " + note.getNote());
                        if (note.hasSynonym()) {
                            System.out.print(" or " + note.getSynonym());
                        }
                        System.out.print("\n");
                        break;
                    case "fingering":
                        System.out.print("\nYour unknown is: FINGERING");
                        fingering = Violin.findFingering(note, string, position);
                        System.out.print("\nYour fingering is: " + fingering);
                        if (fingering.hasSynonym()) {
                            System.out.print(" or " + fingering.getSynonym());
                        }
                        System.out.print("\n");
                        break;
                    case "string":
                        System.out.print("\nYour unknown is: STRING");
                        string = Violin.findString(note, position, fingering);
                        System.out.print("\nYour string is: " + string + "\n");
                        break;
                    case "position":
                        System.out.print("\nYour unknown is: POSITION");
                        position = Violin.findPosition(note, string, fingering);
                        System.out.print("\nYour position is: " + position + "\n");
                        break;
                    case "too many":
                        System.out.print("\nThere are 2 or more unknowns in your input, which invalidates it. "
                                + "Please try again with only 1 unknown!\n");
                        break;
                }
            } catch (InvalidInputException e) {
                System.out.print("\n\nYour input is not valid for some reason.\n"
                        + "Please carefully check your input and make sure it is within the valid range!\n\n"
                        + "As a refresher, here are the program's acceptable ranges:\n" + range);
            } catch (InvalidOutputException e) {
                System.out.print("\n\nThe program's output is not valid for some reason.\n"
                        + "Even though your input is valid, it cannot be used to calculate a valid output!\n\n"
                        + "As a refresher, here are the program's acceptable ranges:\n" + range);
            }
            System.out.print("\nWould you like to continue using the program? Enter \"yes\" or \"no\": ");
            continueLooping = scanner.nextLine();
            if (!continueLooping.equals("no")) {
                System.out.print("\nAlright, let's keep going!\n");
                note = null; fingering = null; string = '\0'; position = 0; // resetting all variables back to initial state
                theUnknown = "none";
                numQuestionMark = 0;
            } else {
                System.out.print("\nOkay. See you next time!\n");
            }
        }
    }

}