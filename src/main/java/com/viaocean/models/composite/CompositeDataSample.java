package com.viaocean.models.composite;

import com.viaocean.models.DataSample;
import com.viaocean.models.FeatureProcessor;
import com.viaocean.models.OutcomeProcessor;

import java.util.List;

public class CompositeDataSample implements DataSample {
    private final List<DataSample> samples;

    public CompositeDataSample(List<DataSample> samples) {
        this.samples = samples;
    }

    public List<DataSample> getSamples() {
        return samples;
    }

    @Override
    public void processValues(FeatureProcessor processor) {
        for (DataSample sample : samples) {
            sample.processValues(processor);
        }
    }

    @Override
    public void processOutcome(OutcomeProcessor processor) {
        CompositeOutcomeProcessor outcomeProcessor = new CompositeOutcomeProcessor();
        for (DataSample sample : samples) {
            sample.processOutcome(outcomeProcessor);
        }
        processor.process(outcomeProcessor.getNumericOutcome());
        processor.process(outcomeProcessor.getBooleanOutcome());
        processor.process(outcomeProcessor.getTextOutcome());
    }
}
