package model;

import model.exceptions.*;

public class Violin {

    // only use these constants to check for validity, not for any other purposes!
    private static final Note VALID_NOTE = new Note("G3");
    private static final char VALID_STRING = 'G';
    private static final int VALID_POSITION = 1;
    private static final Fingering VALID_FINGERING = new Fingering(0);

    public static Note findNote(char string, int position, Fingering fingering)
            throws InvalidInputException {
        checkForValidity(VALID_NOTE, string, position, fingering);
        return new Note(stringOffset(string) + positionOffset(position, fingering) + fingeringOffset(fingering));
    }

    public static char findString(Note note, int position, Fingering fingering)
            throws InvalidInputException, InvalidOutputException {
        checkForValidity(note, VALID_STRING, position, fingering);
        int key = note.getRepr() - positionOffset(position, fingering) - fingeringOffset(fingering);
        if (ReferenceInformation.offsetToString.containsKey(key)) {
            return ReferenceInformation.offsetToString.get(key);
        } else {
            throw new InvalidOutputException();
        }
    }

    public static int findPosition(Note note, char string, Fingering fingering)
            throws InvalidInputException, InvalidOutputException {
        checkForValidity(note, string, VALID_POSITION, fingering);
        int position = reversePositionOffset(note.getRepr() - stringOffset(string) - fingeringOffset(fingering), fingering);
        if (invalidPosition(position)) {
            throw new InvalidOutputException();
        } else {
            return position;
        }
    }

    public static Fingering findFingering(Note note, char string, int position)
            throws InvalidInputException, InvalidOutputException {
        checkForValidity(note, string, position, VALID_FINGERING);
        for (Fingering f : Fingering.allFingerings) {
            if (findNote(string, position, f).equals(note)) {
                return f;
            }
        }
        throw new InvalidOutputException();
    }

    private static void checkForValidity(Note note, char string, int position, Fingering fingering)
            throws InvalidInputException {
        if (invalid(note, string, position, fingering)) {
            throw new InvalidInputException();
        }
    }

    private static boolean invalid(Note note, char string, int position, Fingering fingering) {
        return invalidNote(note) || invalidString(string) || invalidPosition(position) || invalidFingering(fingering);
    }

    private static boolean invalidNote(Note note) {
        return (note.getRepr() < 0) || (note.getRepr() > 50);
    }

    private static boolean invalidString(char string) {
        return !ReferenceInformation.strings.contains(string);
    }

    private static boolean invalidPosition(int position) {
        return (position < 1) || (position > 12);
    }

    private static boolean invalidFingering(Fingering fingering) {
        if (fingering.getNumber() == 0) {
            return !fingering.getModifier().equals("regular");
        } else {
            return (fingering.getNumber() < 0) || (fingering.getNumber() > 4) ||
                    !ReferenceInformation.modifiers.contains(fingering.getModifier());
        }
    }

    private static int stringOffset(char string) {
        return ReferenceInformation.stringToOffset.get(string);
    }

    private static int positionOffset(int position, Fingering fingering) {
        if (fingering.getNumber() == 0) {
            return 0;
        }
        return (int) Math.round((position - 1) * (12.0/7));
    }

    private static int fingeringOffset(Fingering fingering) {
        return ((fingering.getNumber() <= 1) ? (2 * fingering.getNumber()) : (2 * fingering.getNumber() - 1))
                + ReferenceInformation.modifierToOffset.get(fingering.getModifier());
    }

    private static int reversePositionOffset(int positionOffset, Fingering fingering)
            throws InvalidOutputException {
        if (fingering.getNumber() == 0 && positionOffset != 0) {
            throw new InvalidOutputException();
        }
        return (int) Math.round(positionOffset * (7.0/12) + 1);
    }

}