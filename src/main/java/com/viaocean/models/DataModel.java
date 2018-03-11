package com.viaocean.models;

import java.util.List;

public interface DataModel {
    List<String> getFeatureColumnNames();
    List<String> getOutcomeColumnNames();
    DataSample nextSample();
}
