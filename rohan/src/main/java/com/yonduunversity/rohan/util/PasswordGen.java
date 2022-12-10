package com.yonduunversity.rohan.util;

import java.util.Random;

public class PasswordGen {
    public static String generateUserPassaword(int offset) {
        String charPoll = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk" +
                "lmnopqrstuvwxyz!@#$%&";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(offset);
        for (int i = 0; i < offset; i+=1) {
            stringBuilder.append(charPoll.charAt(random.nextInt(charPoll.length())));
        }
        return stringBuilder.toString();
    }
}
