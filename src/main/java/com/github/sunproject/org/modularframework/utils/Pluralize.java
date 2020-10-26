package com.github.sunproject.org.modularframework.utils;

public class Pluralize {
    public static String pluralizeWord(String word, int number) {
        if (number >= 2 || number == 0) {
            return word.concat("s");
        } else {
            return word;
        }
    }
}
