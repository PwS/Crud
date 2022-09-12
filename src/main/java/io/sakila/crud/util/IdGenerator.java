package io.sakila.crud.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class IdGenerator {
    private final Random RANDOM = new SecureRandom();
    private final String ALPHA_NUMERIC = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String generateStringId(int length) {
        return generateRandomString(length);
    }

    public int generateIntId(int length) {
        return generateRandomInt(length);
    }

    private String generateRandomString(int length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            stringBuilder.append(ALPHA_NUMERIC.charAt(RANDOM.nextInt(ALPHA_NUMERIC.length())));
        }
        return new String(stringBuilder);
    }

    private int generateRandomInt(int length) {
        return (int) Math.floor(Math.random() * (length - 1 + 1)) + 1;
    }
}

