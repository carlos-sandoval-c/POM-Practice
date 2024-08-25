package com.globant.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandInt {
    public static int getRandomIndex(int bound) {
        if (bound <= 0)
            return -1;

        Random rand = new Random();
        int randProductIndex = rand.nextInt(bound);
        if (randProductIndex >= bound)
            randProductIndex = bound - 1;

        return randProductIndex;
    }

    public static int[] getRandomIndex(int bound, int totalNumbers) {
        if (bound <= 0)
            return null;

        if (totalNumbers > bound) {
            int[] indexRandArr = new int[bound];
            Arrays.setAll(indexRandArr, i -> i);
            return indexRandArr;
        }

        Set<Integer> indexSet = new HashSet<>();
        int index = 0;
        while (indexSet.size() < totalNumbers && index >= 0) {
            index = RandInt.getRandomIndex(bound); // index < 0 if bound is 0 or less
            indexSet.add(index);
        }

        // Get array of int primitives from set of object type integers
        return indexSet.stream().mapToInt(Number::intValue).toArray();
    }
}
