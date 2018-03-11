package com.viaocean.models.composite;

import com.viaocean.models.DataModel;
import com.viaocean.models.DataSample;
import com.beust.jcommander.internal.Lists;

import java.util.List;

public class CompositeDataModel implements DataModel {
    private final List<DataModel> models;

    public CompositeDataModel(List<DataModel> models) {
        this.models = models;
    }

    public List<DataModel> getModels() {
        return models;
    }

    @Override
    public List<String> getFeatureColumnNames() {
        List<String> allNames = Lists.newArrayList();
        for (DataModel model : models) {
            allNames.addAll(model.getFeatureColumnNames());
        }
        return allNames;
    }

    @Override
    public List<String> getOutcomeColumnNames() {
        return Lists.newArrayList("numeric_outcome", "binary_outcome", "binary_text_outcome");
    }

    @Override
    public CompositeDataSample nextSample() {
        List<DataSample> samples = Lists.newArrayList();
        for (DataModel model : models) {
            samples.add(model.nextSample());
        }
        return new CompositeDataSample(samples);
    }
}
