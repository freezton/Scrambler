package com.scrambler.classes;

public interface Scrambler {

    public String encrypt(String text, String key);

    public String decipher(String text, String key);
}
