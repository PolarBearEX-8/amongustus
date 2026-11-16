package client.util;

import client.util.TimeHelper;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class TabListBotFilter {
    public static final List list2;
    public static final Set set;
    public static String labelText;
    public static final List list;
    public static final List list3;

    public static String[] resolveTextArray(String[] stringArray, String string) {
        return (String[])Stream.concat(Arrays.stream(stringArray), Stream.of(string)).toArray(TabListBotFilter::resolveTextArrayByIndex);
    }

    public static String resolveText(String string, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length() - 1; ++i) {
            if (string.charAt(i) != c) continue;
            stringBuilder.append(string.charAt(i));
            stringBuilder.append(string.charAt(i + 1));
        }
        return stringBuilder.toString();
    }

    private static String[] resolveTextArray(int n) {
        return new String[n];
    }

    public static String getString2(String string) {
        char c = '§';
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char c2 = string.charAt(i);
            if (c2 == c) {
                ++i;
                continue;
            }
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    public static boolean toCharArray(String string, Set set) {
        char[] cArray = string.toCharArray();
        int n = cArray.length;
        for (int i = 0; i < n; ++i) {
            char c = cArray[i];
            if (set.contains(Character.valueOf(c))) continue;
            return false;
        }
        return true;
    }

    public static synchronized boolean matchesText(String string, int n) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        Character[] characterArray = (Character[])string.chars().mapToObj(TabListBotFilter::resolveCharacter).toArray(TabListBotFilter::resolveCharacterArray);
        LinkedList<Character> linkedList = new LinkedList<Character>();
        int n2 = characterArray.length;
        for (int i = 0; i < n2; ++i) {
            char c = characterArray[i].charValue();
            if (list3.contains(Character.valueOf(c)) || list2.contains(Character.valueOf(c)) || list.contains(Character.valueOf(c)) || c == '_') continue;
            linkedList.add(Character.valueOf(c));
        }
        return linkedList.isEmpty() && string.length() >= n;
    }

    private static Character resolveCharacter(int n) {
        return Character.valueOf((char)n);
    }

    public static boolean checkString2(String string) {
        return !string.isEmpty() && !string.contains("§") && !string.contains("#") && !string.contains(" ") && string.length() < 17;
    }

    public static String resolveText(String string, String string2) {
        if (string.length() > string2.length()) {
            return string;
        }
        return string2;
    }

    public static String resolveTextByText(String string, char c) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char c2 = string.charAt(i);
            if (c2 != c && (i <= 0 || string.charAt(i - 1) != c)) continue;
            stringBuilder.append(c2);
        }
        return stringBuilder.toString();
    }

    public static String getText(String[] stringArray) {
        String string = "";
        String[] stringArray2 = stringArray;
        int n = stringArray.length;
        for (int i = 0; i < n; ++i) {
            String string2 = stringArray2[i];
            if (string2.length() <= string.length()) continue;
            string = string2;
        }
        return string;
    }

    private static boolean matchesText(String string, String string2) {
        return !string2.equals(string);
    }

    public static boolean checkString4(String string) {
        Set set;
        return string != null && string.length() < 17 && string.length() >= 3 && TabListBotFilter.toCharArray(string, set = TabListBotFilter.set);
    }

    public static String[] resolveTextArrayByTextArray(String[] stringArray, String string) {
        return (String[])Arrays.stream(stringArray).filter(lambdaArgA -> TabListBotFilter.matchesText(string, lambdaArgA)).toArray(TabListBotFilter::resolveTextArray);
    }

    private static String[] resolveTextArrayByIndex(int n) {
        return new String[n];
    }

    private static Character[] resolveCharacterArray(int n) {
        return new Character[n];
    }

    static {
        String[] stringArray = new String[]{"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_", "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"};
        labelText = stringArray[1];
        set = new HashSet();
        for (char c : stringArray[0].toCharArray()) {
            set.add(Character.valueOf(c));
        }
        list3 = Arrays.asList(Character.valueOf('1'), Character.valueOf('2'), Character.valueOf('3'), Character.valueOf('4'), Character.valueOf('5'), Character.valueOf('6'), Character.valueOf('7'), Character.valueOf('8'), Character.valueOf('9'), Character.valueOf('0'));
        list2 = Arrays.asList(Character.valueOf('a'), Character.valueOf('b'), Character.valueOf('c'), Character.valueOf('d'), Character.valueOf('e'), Character.valueOf('f'), Character.valueOf('g'), Character.valueOf('h'), Character.valueOf('i'), Character.valueOf('j'), Character.valueOf('k'), Character.valueOf('l'), Character.valueOf('m'), Character.valueOf('n'), Character.valueOf('o'), Character.valueOf('p'), Character.valueOf('q'), Character.valueOf('r'), Character.valueOf('s'), Character.valueOf('t'), Character.valueOf('u'), Character.valueOf('v'), Character.valueOf('w'), Character.valueOf('x'), Character.valueOf('y'), Character.valueOf('z'));
        list = Arrays.asList(Character.valueOf('A'), Character.valueOf('B'), Character.valueOf('C'), Character.valueOf('D'), Character.valueOf('E'), Character.valueOf('F'), Character.valueOf('G'), Character.valueOf('H'), Character.valueOf('I'), Character.valueOf('J'), Character.valueOf('K'), Character.valueOf('L'), Character.valueOf('M'), Character.valueOf('N'), Character.valueOf('O'), Character.valueOf('P'), Character.valueOf('Q'), Character.valueOf('R'), Character.valueOf('S'), Character.valueOf('T'), Character.valueOf('U'), Character.valueOf('V'), Character.valueOf('W'), Character.valueOf('X'), Character.valueOf('Y'), Character.valueOf('Z'));
    }
}

