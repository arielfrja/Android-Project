package com.project.arielfaridja.Operator.model.Backend;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.project.arielfaridja.Operator.model.Entities.activity;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.user;

import java.util.ArrayList;

/**
 * Created by c plus on 25/01/2017.
 */

public class Functions {

    ContentResolver resolver;

    void AddUser(user User) {
        ContentValues values = new ContentValues();
        values.put("email", User.getEmail());
        values.put("password", User.getPassword());
        resolver.insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/users"), values);
    }

    void AddBusiness(business Business) {
        ContentValues values = new ContentValues();
        values.put("name", Business.getName());
        values.put("AddressCity", Business.getAddress().getCity());
        values.put("AddressCountry", Business.getAddress().getCountry());
        values.put("AddressNumber", Business.getAddress().getNumber());
        values.put("AddressStreet", Business.getAddress().getStreet());
        values.put("Email", Business.getEmail());
        values.put("PhoneNumber", Business.getPhoneNumber());
        values.put("Website", Business.getWebsite());
        resolver.insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/Business"), values);
    }

    void AddActivity(activity Act) {
        ContentValues values = new ContentValues();
        values.put("ActDes" , Act.getDescription().toString());
        values.put("country", Act.getCountry());
        values.put("cost",Act.getCost());
        values.put("LongDes", Act.getLongDescription());
        values.put("BusinessId", Act.getBusinessId());
        resolver.insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/Activities"), values);

    }

    ContentValues IsChangedSomthing() {
        return null;
    }

    ArrayList<user> GetAllUsers() {
        return null;
    }

    ArrayList<activity> GetAllActivities() {
        return null;
    }

    ArrayList<business> GetAllBusiness() {
        return null;
    }
}
