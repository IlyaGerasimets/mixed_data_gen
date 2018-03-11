package com.viaocean.models.linear;

import com.viaocean.RandomGenerator;
import com.viaocean.models.DataModel;
import com.beust.jcommander.internal.Lists;

import java.util.List;

public class LinearModel implements DataModel {
    private final double intercept;
    private final double[] coefficients;
    private final RandomGenerator random;

    public LinearModel(double intercept, double[] coefficients, RandomGenerator random) {
        this.intercept = intercept;
        this.coefficients = coefficients;
        this.random = random;
    }

    public double getIntercept() {
        return intercept;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public static LinearModel randomModel(RandomGenerator random, int cols) {
        double[] coefficients = new double[cols];
        for (int i = 0; i < cols; i++) {
            coefficients[i] = random.nextValue();
        }
        double intercept = random.nextValue();
        return new LinearModel(intercept, coefficients, random);
    }

    @Override
    public LinearSample nextSample() {
        double outcome = intercept + random.nextNoise();
        double[] values = new double[coefficients.length];
        for (int i = 0; i < coefficients.length; i++) {
            double c = coefficients[i];
            double val = random.nextValue();
            values[i] = val;
            outcome += val * c + random.nextNoise();
        }
        return new LinearSample(values, outcome);
    }

    @Override
    public List<String> getFeatureColumnNames() {
        List<String> res = Lists.newArrayList();
        for (int i = 0; i < coefficients.length; i++) {
            res.add("linear_" + i);
        }
        return res;
    }

    @Override
    public List<String> getOutcomeColumnNames() {
        return Lists.newArrayList("numeric_outcome", "binary_outcome", "binary_text_outcome");
    }
}