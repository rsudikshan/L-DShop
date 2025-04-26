package com.sr.L.DShop.utils;

import java.util.Random;

public class TagHelper {
    public static String generateRandomTag(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder tag = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            tag.append(characters.charAt(index));
        }

        return tag.toString();
    }
}
