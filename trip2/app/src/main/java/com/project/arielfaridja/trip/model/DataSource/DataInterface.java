package com.project.arielfaridja.trip.model.DataSource;

import android.content.Context;

import com.project.arielfaridja.trip.model.Entities.activity;
import com.project.arielfaridja.trip.model.Entities.business;

import java.util.ArrayList;

/**
 * Created by finis on 2/8/2017.
 */
public interface DataInterface {
	public ArrayList<activity> activitiesArrayList = new ArrayList<>();
	public ArrayList<business> businessArrayList = new ArrayList<>();
	public void GetAllDataFromDataBase(Context context);
	public ArrayList<business> GetAllBusinessByCountry(String country);
	public ArrayList<activity> GetAllActivitiesByBusiness(int id);
	public ArrayList<activity> getActivitiesArrayList();
	public ArrayList<business> getBusinessArrayList();
	public ArrayList<activity> GetAllActivitiesByCountry(String country);
}
