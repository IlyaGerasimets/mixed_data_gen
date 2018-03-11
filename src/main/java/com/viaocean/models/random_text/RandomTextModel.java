package com.viaocean.models.random_text;

import com.viaocean.RandomGenerator;
import com.viaocean.models.DataModel;
import com.beust.jcommander.internal.Lists;
import com.beust.jcommander.internal.Sets;

import java.util.List;
import java.util.Set;

public class RandomTextModel implements DataModel {
    private final int columnCount;
    private final String[] domain;
    private final RandomGenerator random;

    public RandomTextModel(int columnCount, String[] domain, RandomGenerator random) {
        this.columnCount = columnCount;
        this.domain = domain;
        this.random = random;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public String[] getDomain() {
        return domain;
    }

    public static String[] randomDomain(RandomGenerator random, int maxLength, int count) {
        Set<String> domain = Sets.newHashSet();
        while (domain.size() < count) {
            int len = random.nextIndex(maxLength);
            char[] val = new char[len];
            for (int j = 0; j < val.length; j++) {
                val[j] = (char)('a' + random.nextIndex('z' - 'a'));
            }
            domain.add(new String(val));
        }
        return domain.toArray(new String[count]);
    }

    public static RandomTextModel randomModel(RandomGenerator random, int columnCount, String[] domain) {
        return new RandomTextModel(columnCount, domain, random);
    }

    @Override
    public RandomTextSample nextSample() {
        double outcome = random.nextValue();
        String[] values = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            int idx = random.nextIndex(domain.length);
            values[i] = domain[idx];
        }
        return new RandomTextSample(values, outcome);
    }

    @Override
    public List<String> getFeatureColumnNames() {
        List<String> res = Lists.newArrayList();
        for (int i = 0; i < columnCount; i++) {
            res.add("random_text_" + i);
        }
        return res;
    }

    @Override
    public List<String> getOutcomeColumnNames() {
        return Lists.newArrayList("numeric_outcome");
    }
}
