package com.scrambler.classes;

import java.util.*;

public final class ColumnMethodScrambler implements Scrambler {

    public int[] getOrder(String key) {
        PriorityQueue<Character> queue = new PriorityQueue<>(key.length());
        for (char c: key.toCharArray())
            queue.add(c);
        int[] order = new int[key.length()];
        int i = 0;
        while (!queue.isEmpty()) {
            order[i++] = key.indexOf(queue.peek());
            key = key.replaceFirst(Objects.requireNonNull(queue.poll()).toString(), "-");
        }
        return order;
    }
    public static String getCorrectString(String string) {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();
        for (Character c: string.toUpperCase().toCharArray()) {
            if (alphabet.contains(c.toString()))
                result.append(c);
        }
        return result.toString();
    }

    @Override
    public String encrypt(String rawText, String rawKey) {
        String key = getCorrectString(rawKey);
        if (key.isEmpty())
            return null;
        String text = getCorrectString(rawText);

        int rows = text.length() / key.length() + 1;
        int columns = key.length();
        Character[][] table = new Character[rows][columns];
        for (int i = 0; i < text.length(); i++)
            table[i / key.length()][i % key.length()] = text.charAt(i);

        int[] order = getOrder(key);
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (table[i][order[j]] != null)
                    result.append(table[i][order[j]]);
            }
        }
        return result.toString();
    }
    @Override
    public String decipher(String rawText, String rawKey) {
        String key = getCorrectString(rawKey);
        if (key.isEmpty())
            return null;
        String text = getCorrectString(rawText);

        int rows = text.length() / key.length() + 1;
        int columns = key.length();
        Character[][] table = new Character[rows][columns];
        int[] order = getOrder(key);
        int t = 0;
        for (int j = 0; j < columns; j++) {
            for (int i = 0; i < rows; i++) {
                if (i*columns + order[j] < text.length())
                    table[i][order[j]] = text.charAt(t++);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++)
            result.append(table[i / key.length()][i % key.length()]);
        return result.toString();
    }
}
//        UnicastRemoteObject
