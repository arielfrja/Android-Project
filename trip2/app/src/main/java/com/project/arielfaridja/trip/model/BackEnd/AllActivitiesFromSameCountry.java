package com.project.arielfaridja.trip.model.BackEnd;

import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.activity;

import java.util.ArrayList;

/**
 * Created by Ariel Faridja on 02/03/2017.
 */

public class AllActivitiesFromSameCountry {

    String country;
    ArrayList<activity> activities = new ArrayList<>();
    DataInterface DS = DataFactory.getData();
    public AllActivitiesFromSameCountry(String country)
    {
        this.country = country;
        activities = DS.GetAllActivitiesByCountry(country);
    }

    public String getCountry() {
        return country;
    }

    public ArrayList<activity> getActivities() {
        return activities;
    }

    public DataInterface getDS() {
        return DS;
    }
}
