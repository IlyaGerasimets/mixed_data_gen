package com.viaocean;

import com.beust.jcommander.Parameter;

public class DataModelGeneratorArguments {
    @Parameter(names = "-folder", description = "Path to the folder to save the generated data files")
    private String folder = ".";

    @Parameter(names = "-prefix", description = "File name perfix")
    private String prefix = "random";

    @Parameter(names = "-seed", description = "Pseudo random number generator initialization value")
    private long seed = 0;

    @Parameter(names = "-training", description = "Number of sample to generate for training subset")
    private long trainingRowCount = 10000;

    @Parameter(names = "-testing", description = "Number of sample to generate for training subset")
    private long testingRowCount = 2000;

    @Parameter(names = "-linear", description = "Number of linear model coefficients (not including intercept)")
    private int linearModelColumnCount = 100;

    @Parameter(names = "-numeric", description = "Number of random numeric columns")
    private int randomNumericColumnCount = 100;

    @Parameter(names = "-text", description = "Number of random text columns")
    private int randomTextColumnCount = 100;

    @Parameter(names = "-max_length", description = "Maximum length of random strings")
    private int randomTextMaxLength = 30;

    @Parameter(names = "-domain_size", description = "Maximum number of distinct random strings")
    private int randomTextDomainSize = 100;


    public String getFolder() {
        return folder;
    }

    public DataModelGeneratorArguments setFolder(String folder) {
        this.folder = folder;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public DataModelGeneratorArguments setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public long getSeed() {
        return seed;
    }

    public DataModelGeneratorArguments setSeed(int seed) {
        this.seed = seed;
        return this;
    }

    public long getTrainingRowCount() {
        return trainingRowCount;
    }

    public DataModelGeneratorArguments setTrainingRowCount(int trainingRowCount) {
        this.trainingRowCount = trainingRowCount;
        return this;
    }

    public long getTestingRowCount() {
        return testingRowCount;
    }

    public DataModelGeneratorArguments setTestingRowCount(int testingRowCount) {
        this.testingRowCount = testingRowCount;
        return this;
    }

    public int getLinearModelColumnCount() {
        return linearModelColumnCount;
    }

    public DataModelGeneratorArguments setLinearModelColumnCount(int linearModelColumnCount) {
        this.linearModelColumnCount = linearModelColumnCount;
        return this;
    }

    public int getRandomNumericColumnCount() {
        return randomNumericColumnCount;
    }

    public DataModelGeneratorArguments setRandomNumericColumnCount(int randomNumericColumnCount) {
        this.randomNumericColumnCount = randomNumericColumnCount;
        return this;
    }

    public int getRandomTextColumnCount() {
        return randomTextColumnCount;
    }

    public DataModelGeneratorArguments setRandomTextColumnCount(int randomTextColumnCount) {
        this.randomTextColumnCount = randomTextColumnCount;
        return this;
    }

    public int getRandomTextMaxLength() {
        return randomTextMaxLength;
    }

    public DataModelGeneratorArguments setRandomTextMaxLength(int randomTextMaxLength) {
        this.randomTextMaxLength = randomTextMaxLength;
        return this;
    }

    public int getRandomTextDomainSize() {
        return randomTextDomainSize;
    }

    public DataModelGeneratorArguments setRandomTextDomainSize(int randomTextDomainSize) {
        this.randomTextDomainSize = randomTextDomainSize;
        return this;
    }
}