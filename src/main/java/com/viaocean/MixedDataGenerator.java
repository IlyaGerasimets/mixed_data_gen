package com.viaocean;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;

public class MixedDataGenerator {

    public static void main(String[] argv) {
        DataModelGeneratorArguments arguments = new DataModelGeneratorArguments();

        try {
            JCommander.newBuilder()
                    .addObject(arguments)
                    .programName("DataModelGenerator")
                    .build().parse(argv);
        }
        catch (ParameterException e) {
            System.err.println(e.getMessage());
            e.getJCommander().usage();
            return;
        }

        try {
            DataModelGeneratorEngine engine = new DataModelGeneratorEngine();
            engine.process(arguments);
            System.out.println("Success!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}