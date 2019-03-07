package com.project.arielfaridja.trip.model.DataSource;

/**
 * Created by finis on 2/8/2017.
 */

public class DataFactory {
	private static DataInterface instance = null;

	public static DataInterface getData() {
		if (instance == null)
			instance = new DataList();
		return instance;
	}
}
