package com.project.arielfaridja.trip.model.Entities;

import android.content.Context;

import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;

import java.util.ArrayList;

/**
 * Created by Ariel Faridja on 12/02/2017.
 */

public class ExpandableItem {
    business business =  new business();
    ArrayList<activity> activities;
    DataInterface DS = DataFactory.getData();

    public ExpandableItem(business b) {
        business.setEmail(b.getEmail());
        business.setAddress(b.getAddress());
        business.setID(b.getID());
        business.setName(b.getName());
        business.setPhoneNumber(b.getPhoneNumber());
        business.setWebsite(b.getWebsite());
        activities = DS.GetAllActivitiesByBusiness(business.getID());
    }

    public com.project.arielfaridja.trip.model.Entities.business getBusiness() {
        return business;
    }

    public void setBusiness(com.project.arielfaridja.trip.model.Entities.business business) {
        this.business = business;
    }

    public ArrayList<activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<activity> activities) {
        this.activities = activities;
    }

    public DataInterface getDS() {
        return DS;
    }

    public void setDS(DataInterface DS) {
        this.DS = DS;
    }
}
