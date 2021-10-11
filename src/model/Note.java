package model;

public class Note {

    private final int repr;
    private final String note;

    public Note(String notename, int octave) {
        note = notename + octave;
        int constant = ReferenceInformation.higherThanC.get(notename) ? 4 : 3; // see ChoosingShiftingConstant.png
        repr = ReferenceInformation.noteMap.get(notename) + 12 * (octave - constant);
    }

    public Note(String fullNote) {
        note = fullNote;
        int constant = ReferenceInformation.higherThanC.get(getNotename()) ? 4 : 3; // see ChoosingShiftingConstant.png
        repr = ReferenceInformation.noteMap.get(getNotename()) + 12 * (getOctave() - constant);
    }

    public Note(int representation) {
        repr = representation;
        note = reprToNote();
    }

    public int getRepr() {
        return repr;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Note)) {
            return false;
        }
        return repr == ((Note) other).repr;
    }

    // NOTE: only returns one of the notes if it has a synonym, and always returns the sharp one in this case
    private String reprToNote() {
        String notename = ReferenceInformation.noteMapReversed.get(repr % 12);
        int constant = ReferenceInformation.higherThanC.get(notename) ? 4 : 3; // see ChoosingShiftingConstant.png
        int octave = repr / 12 + constant;
        return notename + octave;
    }

    public boolean hasSynonym() {
        return ReferenceInformation.synonymableNotes1.contains(getNotename()) ||
                ReferenceInformation.synonymableNotes2.contains(getNotename());
    }

    // REQUIRES: this.hasSynonym() is true
    public String getSynonym() {
        if (!hasSynonym()) {
            throw new IllegalStateException();
        }
        return ReferenceInformation.noteSynonyms.get(getNotename()) + getOctave();
    }

    private String getNotename() {
        return note.substring(0, note.length() - 1);
    }

    private int getOctave() {
        return Integer.parseInt(Character.toString(note.charAt(note.length() - 1)));
    }

}