package com.viaocean.models;

public interface OutcomeProcessor {
    void process(String val);
    void process(boolean val);
    void process(double val);
}
