package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fingering {

    public static final List<Fingering> allFingerings = allFingerings();

    private final int fingerNumber;
    private final String modifier;

    public Fingering(int fingerNum, String mod) {
        fingerNumber = fingerNum;
        modifier = mod;
    }

    public Fingering(String fullFingering) {
        fingerNumber = Integer.parseInt(Character.toString(fullFingering.charAt(0)));
        modifier = ReferenceInformation.symbolToModifier.get(fullFingering.charAt(1));
    }

    public Fingering(int fingerNum) {
        fingerNumber = fingerNum;
        modifier = "regular";
    }

    public int getNumber() {
        return fingerNumber;
    }

    public String getModifier() {
        return modifier;
    }

    @Override
    public String toString() {
        return Integer.toString(fingerNumber) + ReferenceInformation.modifierToSymbol.get(modifier);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Fingering)) {
            return false;
        }
        return toString().equals(other.toString());
    }

    public boolean hasSynonym() {
        return ReferenceInformation.synonymableFingerings1.contains(toString()) ||
                ReferenceInformation.synonymableFingerings2.contains(toString());
    }

    // REQUIRES: this.hasSynonym() is true
    public String getSynonym() {
        if (!hasSynonym()) {
            throw new IllegalStateException();
        }
        return ReferenceInformation.fingeringSynonyms.get(toString());
    }

    private static List<Fingering> allFingerings() {
        List<Fingering> fingerings = new ArrayList<>(Collections.singletonList(new Fingering(0)));
        for (int i = 1; i <= 4; i++) {
            for (String modifier : ReferenceInformation.modifiers) {
                fingerings.add(new Fingering(i, modifier));
            }
        }
        return fingerings;
    }

}