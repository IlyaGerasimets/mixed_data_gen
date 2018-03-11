package com.viaocean;

import com.viaocean.models.DataModel;
import com.viaocean.models.DataSample;
import com.viaocean.models.composite.CompositeDataModel;
import com.viaocean.models.linear.LinearModel;
import com.viaocean.models.random_numeric.RandomNumericModel;
import com.viaocean.models.random_text.RandomTextModel;
import com.beust.jcommander.internal.Lists;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class DataModelGeneratorEngine {
    public void process(DataModelGeneratorArguments arguments) throws IOException {
        RandomGenerator baseRNG = new RandomGenerator(arguments.getSeed());

        RandomGenerator g1 = baseRNG.nextGenerator();
        RandomGenerator g2 = baseRNG.nextGenerator();
        RandomGenerator g3 = baseRNG.nextGenerator();
        RandomGenerator g4 = baseRNG.nextGenerator();

        int totalColumns = 3
                + arguments.getLinearModelColumnCount()
                + arguments.getRandomNumericColumnCount()
                + arguments.getRandomTextColumnCount();
        String prefix = arguments.getPrefix() + "_" + UUID.randomUUID() + "_col_" + totalColumns;

        Path folder = Paths.get(arguments.getFolder());


        List<DataModel> allModels = Lists.newArrayList();

        if(arguments.getLinearModelColumnCount() > 0) {
            LinearModel model1 = LinearModel.randomModel(g1, arguments.getLinearModelColumnCount());
            allModels.add(model1);
            saveCoefficients(folder.resolve(prefix + "_coef.txt").toFile(), model1);
        }

        if(arguments.getRandomNumericColumnCount() > 0) {
            RandomNumericModel model2 = RandomNumericModel.randomModel(g2, arguments.getRandomNumericColumnCount());
            allModels.add(model2);
        }

        if(arguments.getRandomTextColumnCount() > 0) {
            String[] domain = RandomTextModel.randomDomain(g3, arguments.getRandomTextMaxLength(), arguments.getRandomTextDomainSize());
            RandomTextModel model3 = RandomTextModel.randomModel(g4, arguments.getRandomTextColumnCount(), domain);
            allModels.add(model3);
        }

        long trainingRows = arguments.getTrainingRowCount();
        long testingRows = arguments.getTestingRowCount();

        CompositeDataModel finalModel = new CompositeDataModel(allModels);
        saveModel(folder, prefix + "_row_" + trainingRows + "_train", finalModel, trainingRows);
        saveModel(folder, prefix + "_row_" + testingRows + "_test", finalModel, testingRows);
    }

    private static void saveModel(Path folder, String name, CompositeDataModel model, long rows) throws IOException {
        try (FileOutputStream fileStream = new FileOutputStream(folder.resolve(name + ".csv.zip").toFile())) {
            try (ZipOutputStream zipStream = new ZipOutputStream(fileStream)) {
                zipStream.putNextEntry(new ZipEntry(name + ".csv"));
                try (BufferedOutputStream stream = new BufferedOutputStream(zipStream)) {
                    saveDataset(stream, model, rows);
                }
            }
        }
    }

    private static void saveDataset(OutputStream stream, DataModel model, long rows) throws IOException {
        try (PrintStream printStream = new PrintStream(stream)) {
            DataModelWriter writer = new DataModelWriter(printStream, ",");
            writeColumnNames(writer, model);
            for (long i = 1; i <= rows; i++) {
                writeSample(writer, model.nextSample());
                if(i % 10000 == 0) {
                    System.out.print(".");
                }
                if(i % 200000 == 0) {
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(" " + rows + " rows");
        }
    }

    private static void writeColumnNames(DataModelWriter writer, DataModel model) {
        writer.beginSample();
        for (String col : model.getFeatureColumnNames()) {
            writer.process(col);
        }
        for (String col : model.getOutcomeColumnNames()) {
            writer.process(col);
        }
        writer.completeSample();
    }

    private static void writeSample(DataModelWriter writer, DataSample sample) {
        writer.beginSample();
        sample.processValues(writer);
        sample.processOutcome(writer);
        writer.completeSample();
    }

    private static void saveCoefficients(File file, LinearModel model) throws IOException {
        try (FileOutputStream fileStream = new FileOutputStream(file)) {
            try (BufferedOutputStream stream = new BufferedOutputStream(fileStream)) {
                saveCoefficients(stream, model);
            }
        }
    }

    private static void saveCoefficients(OutputStream stream, LinearModel model) {
        try (PrintStream printStream = new PrintStream(stream)) {
            DataModelWriter writer = new DataModelWriter(printStream, "\r\n");

            writer.beginSample();
            for (double c : model.getCoefficients()) {
                writer.process(c);
            }
            writer.process(model.getIntercept());
            writer.completeSample();
        }
    }
}
