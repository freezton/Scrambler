package com.scrambler.classes;

public class VigenereMethodScrambler implements Scrambler {

    final static String alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
//    final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private char[][] table;

    private void createTable() {
        table = new char[alphabet.length()][alphabet.length()];
        for (int i = 0; i < alphabet.length(); i++)
            for (int j = 1; j <= alphabet.length(); j++)
                table[i][j-1] = alphabet.charAt((i + j) % alphabet.length());
    }

    public VigenereMethodScrambler() {
        createTable();
    }

    private String getCorrectString(String string) {
        StringBuilder result = new StringBuilder();
        for (Character c: string.toUpperCase().toCharArray()) {
            if (alphabet.contains(c.toString()))
                result.append(c);
        }
        return result.toString();
    }

    private String getCorrectKey(String text, String rawKey) {
        StringBuilder key = new StringBuilder(getCorrectString(rawKey));
        if (key.length() < text.length()) {
            int oldLength = key.length();
            while (key.length() < text.length()) {
                key.append(alphabet.charAt((alphabet.indexOf(key.charAt(key.length() % oldLength)) + key.length() / oldLength) % alphabet.length()));
            }
        } else {
            return key.substring(0, text.length());
        }
        return key.toString();
    }

    @Override
    public String encrypt(String rawText, String rawKey) {
        String text = getCorrectString(rawText);
        String key = getCorrectKey(text, rawKey);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(table[alphabet.indexOf(text.charAt(i))][alphabet.indexOf(key.charAt(i))]);
        }
        return result.toString();
    }

    @Override
    public String decipher(String rawText, String rawKey) {
        String text = getCorrectString(rawText);
        String key = getCorrectKey(text, rawKey);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            int index = 0;
            while (table[alphabet.indexOf(key.charAt(i))][index] != text.charAt(i))
                index++;
            result.append(alphabet.charAt(index));
        }
        return result.toString();
    }
}
