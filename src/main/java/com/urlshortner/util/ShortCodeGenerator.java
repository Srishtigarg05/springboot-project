package com.urlshortner.util;

import java.util.Random;

public class ShortCodeGenerator {

    private static final String CHARSET =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateCode(int length) {

        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; i++) {
            code.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }

        return code.toString();
    }
}