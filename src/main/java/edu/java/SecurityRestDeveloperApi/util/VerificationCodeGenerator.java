package edu.java.SecurityRestDeveloperApi.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class VerificationCodeGenerator {

    private static final int min = 100000;

    private static final int max = 999999;

    private final Random random;

    @Autowired
    public VerificationCodeGenerator(Random random) {
        this.random = random;
    }

    public int generate() {
        return random.nextInt((max - min) + 1) + min;
    }
}
