package com.project.arielfaridja.Operator.model.Datasource;

/**
 * Created by c plus on 10/01/2017.
 */

public class DataWorkFactory {
    private static FunctionsInterface Instance = null;

    DataWorkFactory() {
    }

    public static FunctionsInterface GetFunctions() {
        if (Instance == null)
            Instance = new ListFunctions();
        return Instance;
    }
}
