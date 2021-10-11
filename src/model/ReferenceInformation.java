package model;

import java.util.*;

public class ReferenceInformation {

    private static final List<String> notenameList = Arrays.asList(
            "G", "G#", "Ab", "A", "A#", "Bb", "B", "C", "C#",
            "Db", "D", "D#", "Eb", "E", "F", "F#", "Gb");

    public static final List<String> modifiers = Arrays.asList("extended", "regular", "retracted");

    public static final Map<String, Integer> noteMap = makeMap(
            notenameList,
            new ArrayList<>(Arrays.asList(0, 1, 1, 2, 3, 3, 4, 5, 6, 6, 7, 8, 8, 9, 10, 11, 11)));

    public static final Map<Integer, String> noteMapReversed = makeMap(
            new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)),
            new ArrayList<>(Arrays.asList("G", "G#", "A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#")));

    public static final Map<String, Boolean> higherThanC = makeMap(
            notenameList,
            new ArrayList<>(Arrays.asList(
                    false, false, false, false, false, false, false, true, true,
                    true, true, true, true, true, true, true, true)));

    public static final Map<Character, String> symbolToModifier = makeMap(
            new ArrayList<>(Arrays.asList('+', '=', '-')),
            modifiers);

    public static final Map<String, Character> modifierToSymbol = makeMap(
            modifiers,
            new ArrayList<>(Arrays.asList('+', '\0', '-')));

    public static final List<String> synonymableFingerings1 = Arrays.asList("1\0", "2\0", "2+", "3+");
    public static final List<String> synonymableFingerings2 = Arrays.asList("2-", "1+", "3-", "4-");

    public static final Map<String, String> fingeringSynonyms = makeMapBothWays(
            synonymableFingerings1,
            synonymableFingerings2);

    public static final List<String> synonymableNotes1 = Arrays.asList("G#", "A#", "C#", "D#", "F#");
    public static final List<String> synonymableNotes2 = Arrays.asList("Ab", "Bb", "Db", "Eb", "Gb");

    public static final Map<String, String> noteSynonyms = makeMapBothWays(
            synonymableNotes1,
            synonymableNotes2);

    public static final List<Character> strings = Arrays.asList('G', 'D', 'A', 'E');
    private static final List<Integer> stringOffsets = Arrays.asList(0, 7, 14, 21);

    public static final Map<Character, Integer> stringToOffset = makeMap(strings, stringOffsets);
    public static final Map<Integer, Character> offsetToString = makeMap(stringOffsets, strings);

    public static final Map<String, Integer> modifierToOffset = makeMap(
            modifiers,
            new ArrayList<>(Arrays.asList(1, 0, -1)));

    private static <A, B> Map<A, B> makeMap(List<A> l1, List<B> l2) {
        Map<A, B> map = new HashMap<>();
        for (int i = 0; i < l1.size(); i++) {
            map.put(l1.get(i), l2.get(i));
        }
        return map;
    }

    private static <A> Map<A, A> makeMapBothWays(List<A> l1, List<A> l2) {
        Map<A, A> map = new HashMap<>();
        for (int i = 0; i < l1.size(); i++) {
            map.put(l1.get(i), l2.get(i));
            map.put(l2.get(i), l1.get(i));
        }
        return map;
    }

}