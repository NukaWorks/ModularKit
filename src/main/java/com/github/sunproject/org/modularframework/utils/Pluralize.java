package com.github.sunproject.org.modularframework.utils;

/**
 * @since 1.0
 * @author sundev79 (sundev79.sunproject@gmail.com)
 * Class for pluralizing words.
 */

public class Pluralize {
    public static String pluralizeWord(String word, int number) {
        if (number >= 2 || number == 0) {
            return word.concat("s");
        } else {
            return word;
        }
    }
}
