package com.viaocean;

import com.viaocean.models.FeatureProcessor;
import com.viaocean.models.OutcomeProcessor;

import java.io.PrintStream;

public class DataModelWriter implements FeatureProcessor, OutcomeProcessor {
    private static final String FLOAT_FORMAT = "%.8f"; // formatting for floating point numbers

    private final PrintStream stream;
    private boolean needSeparator;
    private final String separator;

    public DataModelWriter(PrintStream stream) {
        this(stream, ",");
    }

    public DataModelWriter(PrintStream stream, String separator) {
        this.stream = stream;
        this.separator = separator;
    }

    @Override
    public void process(String val) {
        writeSeparator();
        if(val == null || "".equals(val) || val.contains(" ") || val.contains("\t")) {
            stream.print('\"');
            stream.print(val);
            stream.print('\"');
        } else {
            stream.print(val);
        }
    }

    @Override
    public void process(boolean val) {
        writeSeparator();
        stream.printf("%.1f", val ? 1.0 : 0.0);
    }

    @Override
    public void process(double val) {
        writeSeparator();
        stream.printf(FLOAT_FORMAT, val);
    }

    private void writeSeparator() {
        if(needSeparator) {
            stream.print(separator);
        }
        needSeparator = true;
    }

    public void beginSample() {
    }

    public void completeSample() {
        stream.println();
        needSeparator = false;
    }
}
