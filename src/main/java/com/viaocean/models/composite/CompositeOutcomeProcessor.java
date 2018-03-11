package com.viaocean.models.composite;

import com.viaocean.models.OutcomeProcessor;

public class CompositeOutcomeProcessor implements OutcomeProcessor {
    private double numericOutcome;
    private String textOutcome = "";
    private boolean booleanOutcome = false;

    public double getNumericOutcome() {
        return numericOutcome;
    }

    public String getTextOutcome() {
        return textOutcome;
    }

    public boolean getBooleanOutcome() {
        return booleanOutcome;
    }

    @Override
    public void process(String val) {
        textOutcome += val;
    }

    @Override
    public void process(double val) {
        numericOutcome += val;
    }

    @Override
    public void process(boolean val) {
        booleanOutcome |= val;
    }
}
