package model;

import model.exceptions.InvalidInputException;
import model.exceptions.InvalidOutputException;

public class Validation {

    private final Note note;
    private final char string;
    private final int position;
    private final Fingering fingering;
    private final boolean valid;

    private boolean strangeString = false;
    private boolean strangePosition = false;
    private boolean strangeFingering = false;

    public Validation(Note note, char string, int position, Fingering fingering)
            throws InvalidInputException {
        if (isValid(note, string, position, fingering)) {
            this.note = note;
            this.string = string;
            this.position = position;
            this.fingering = fingering;
            this.valid = true;
        } else {
            this.note = Violin.findNote(string, position, fingering);
            this.string = findString(note, position, fingering);
            this.position = findPosition(note, string, fingering);
            this.fingering = findFingering(note, string, position);
            this.valid = false;
        }
    }

    private static boolean isValid(Note note, char string, int position, Fingering fingering)
            throws InvalidInputException {
        return Violin.findNote(string, position, fingering).equals(note);
    }

    private char findString(Note note, int position, Fingering fingering)
            throws InvalidInputException {
        try {
            return Violin.findString(note, position, fingering);
        } catch (InvalidOutputException e) {
            strangeString = true;
            return '\0';
        }
    }

    private int findPosition(Note note, char string, Fingering fingering)
            throws InvalidInputException {
        try {
            return Violin.findPosition(note, string, fingering);
        } catch (InvalidOutputException e) {
            strangePosition = true;
            return -1;
        }
    }

    private Fingering findFingering(Note note, char string, int position)
            throws InvalidInputException {
        try {
            return Violin.findFingering(note, string, position);
        } catch (InvalidOutputException e) {
            strangeFingering = true;
            return null;
        }
    }

    public Note note() {
        return note;
    }

    public char string() {
        return string;
    }

    public int position() {
        return position;
    }

    public Fingering fingering() {
        return fingering;
    }

    public boolean valid() {
        return valid;
    }

    public boolean strangeString() {
        return strangeString;
    }

    public boolean strangePosition() {
        return strangePosition;
    }

    public boolean strangeFingering() {
        return strangeFingering;
    }

}