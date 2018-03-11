package com.viaocean;

import java.util.Random;

public class RandomGenerator {
    private final Random random;

    private static final int SCALE = 200; // from -100 to 100
    private static final int NOISE_LEVEL = 2; // Standard deviation for Gaussian noise with zero mean

    public RandomGenerator(long seed) {
        random = new Random(seed);
    }

    public RandomGenerator nextGenerator() {
        return new RandomGenerator(random.nextLong());
    }

    public double nextValue() {
        return random.nextDouble() * SCALE - SCALE / 2;
    }

    public int nextIndex(int max) {
        return random.nextInt(max);
    }

    public double nextNoise() {
        return random.nextGaussian() * NOISE_LEVEL;
    }
}
