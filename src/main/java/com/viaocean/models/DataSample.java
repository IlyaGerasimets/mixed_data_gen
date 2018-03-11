package com.viaocean.models;

public interface DataSample {
    void processValues(FeatureProcessor processor);
    void processOutcome(OutcomeProcessor processor);
}
