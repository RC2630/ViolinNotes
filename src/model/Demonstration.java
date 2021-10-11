package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// this class is in the model package (not the io package) because it is merely a demonstration of various other classes
// found in the model package. DO NOT USE THIS AS THE ACTUAL PROGRAM'S STARTING POINT!
public class Demonstration {

    public static void main(String[] args) {

        // demonstration of the Note class
        Note note1 = new Note("A#", 6); // 39
        Note note2 = new Note("Db5"); // 18
        Note note3 = new Note("C#5"); // 18
        System.out.print("\nnote1 = " + note1.getNote()
                + "\nnote2 = " + note2.getNote()
                + "\nnote3 = " + note3.getNote()
                + "\n\nnote1 repr = " + note1.getRepr()
                + "\nnote2 repr = " + note2.getRepr()
                + "\nnote3 repr = " + note3.getRepr()
                + "\n\nnote1 equals note2? " + note1.equals(note2)
                + "\nnote1 equals note3? " + note1.equals(note3)
                + "\nnote2 equals note3? " + note2.equals(note3) + "\n");

        // demonstration of the Fingering class
        Fingering f1 = new Fingering(2, "retracted");
        Fingering f2 = new Fingering("2-");
        Fingering f3 = new Fingering("4+");
        System.out.print("\nfingering 1 = " + f1
                + "\nfingering 2 = " + f2
                + "\nfingering 3 = " + f3
                + "\n\nfingering1 equals fingering2? " + f1.equals(f2)
                + "\nfingering1 equals fingering3? " + f1.equals(f3)
                + "\nfingering2 equals fingering3? " + f2.equals(f3) + "\n");

        // the single integer constructor for Fingering
        Fingering f4 = new Fingering(3);
        System.out.print("\nfingering4 = " + f4 + "\n");

        // retracted, regular, extended fingerings
        System.out.print("\nOne of each kind of fingering modifier (read the source code to see different constructor usages):"
                + "\n3 retracted = " + new Fingering("3-") + " or " + new Fingering(3, "retracted")
                + "\n3 regular   = " + new Fingering(3) + "  or " + new Fingering("3=") + "  or " + new Fingering(3, "regular")
                + "\n3 extended  = " + new Fingering("3+") + " or " + new Fingering(3, "extended") + "\n");

        // representation constructor for Note
        Note note4 = new Note("F7"); // 46
        Note note5 = new Note(46); // F7
        System.out.print("\nnote4 = " + note4.getNote()
                + "\nnote5 = " + note5.getNote()
                + "\nnote4 repr = " + note4.getRepr()
                + "\nnote5 repr = " + note5.getRepr()
                + "\nAre they the same note? " + note4.equals(note5) + "\n");
        Note note6 = new Note("D#6"); // 32
        Note note7 = new Note(32); // D#6
        System.out.print("\nnote6 = " + note6.getNote()
                + "\nnote7 = " + note7.getNote()
                + "\nnote6 repr = " + note6.getRepr()
                + "\nnote7 repr = " + note7.getRepr()
                + "\nAre they the same note? " + note6.equals(note7) + "\n\n");

        // synonyms for notes
        List<Note> notes = new ArrayList<>(Arrays.asList(note1, note2, note3, note4, note5, note6, note7));
        for (Note note : notes) {
            System.out.print("current note = " + note.getNote()
                    + "\nDoes this note have a synonym? " + note.hasSynonym() + "\n");
        }
        System.out.print("\nFor all notes that do have synonyms:\n");
        for (Note note : notes) {
            if (note.hasSynonym()) {
                System.out.print("original note = " + note.getNote()
                        + "\nsynonymous note = " + note.getSynonym() + "\n");
            }
        }

        // synonyms for fingerings
        List<Fingering> fingerings = new ArrayList<>(Arrays.asList(f1, f2, f3, f4,
                new Fingering("4-"), new Fingering(2), new Fingering("3+"), new Fingering(0)));
        System.out.print("\n");
        for (Fingering f : fingerings) {
            System.out.print("current fingering = " + f
                    + "\nDoes this fingering have a synonym? " + f.hasSynonym() + "\n");
        }
        System.out.print("\nFor all fingerings that do have synonyms:\n");
        for (Fingering f : fingerings) {
            if (f.hasSynonym()) {
                System.out.print("original fingering = " + f
                        + "\nsynonymous fingering = " + f.getSynonym() + "\n");
            }
        }

        // all fingerings
        System.out.print("\nAll possible fingerings:");
        for (Fingering f : Fingering.allFingerings) {
            System.out.print(" " + f);
        }
        System.out.print("\n");

    }

}