package com.asico.hr.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UniqueRandomGenerator {
    private static final Set<Integer> usedNumbers = new HashSet<>();
    private static final Random random = new Random();

    public static int generateUnique8Digit() {
        int number;
        do {
            number = 10000000 + random.nextInt(90000000); // ensures 8 digits
        } while (!usedNumbers.add(number));
        return number;
    }
}
