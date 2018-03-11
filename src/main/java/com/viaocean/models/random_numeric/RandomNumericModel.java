package com.viaocean.models.random_numeric;

import com.viaocean.RandomGenerator;
import com.viaocean.models.DataModel;
import com.beust.jcommander.internal.Lists;

import java.util.List;

public class RandomNumericModel implements DataModel {
    private final int columnCount;
    private final RandomGenerator random;

    public RandomNumericModel(int columnCount, RandomGenerator random) {
        this.columnCount = columnCount;
        this.random = random;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public static RandomNumericModel randomModel(RandomGenerator random, int columnCount) {
        return new RandomNumericModel(columnCount, random);
    }

    @Override
    public RandomNumericSample nextSample() {
        double outcome = 0;
        double[] values = new double[columnCount];
        for (int i = 0; i < columnCount; i++) {
            double val = random.nextValue();
            values[i] = val;
        }
        return new RandomNumericSample(values, outcome);
    }

    @Override
    public List<String> getFeatureColumnNames() {
        List<String> res = Lists.newArrayList();
        for (int i = 0; i < columnCount; i++) {
            res.add("random_number_" + i);
        }
        return res;
    }

    @Override
    public List<String> getOutcomeColumnNames() {
        return Lists.newArrayList("numeric_outcome");
    }
}
