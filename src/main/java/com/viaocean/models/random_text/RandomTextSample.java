package com.viaocean.models.random_text;


import com.viaocean.models.DataSample;
import com.viaocean.models.FeatureProcessor;
import com.viaocean.models.OutcomeProcessor;

public class RandomTextSample implements DataSample {
    private final String[] values;
    private final double outcome;

    public RandomTextSample(String[] values, double outcome) {
        this.values = values;
        this.outcome = outcome;
    }

    public String[] getValues() {
        return values;
    }

    public double getOutcome() {
        return outcome;
    }

    @Override
    public void processValues(FeatureProcessor processor) {
        for (int i = 0; i < values.length; i++) {
            processor.process(values[i]);
        }
    }

    @Override
    public void processOutcome(OutcomeProcessor processor) {
        processor.process(outcome);
    }
}