package com.project.arielfaridja.trip.model.DataSource;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import com.project.arielfaridja.trip.model.Entities.ActDes;
import com.project.arielfaridja.trip.model.Entities.Address;
import com.project.arielfaridja.trip.model.Entities.Date;
import com.project.arielfaridja.trip.model.Entities.activity;
import com.project.arielfaridja.trip.model.Entities.business;

import java.util.ArrayList;

/**
 * Created by finis on 2/8/2017.
 */

public class DataList implements DataInterface {
    // include businessArrayList, and activitiesArrayList from the interface
    Cursor ActivitiesCursor;
    Cursor BusinessCursor;



    public void GetAllDataFromDataBase(Context context) {
        final Context c = context;
        new AsyncTask<Void, Void, Uri>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Uri uri) {
                super.onPostExecute(uri);
                if(ActivitiesCursor != null)
                for (ActivitiesCursor.moveToFirst(); !ActivitiesCursor.isAfterLast(); ActivitiesCursor.moveToNext()) {
                    activitiesArrayList.add(new activity(
                            ActDes.valueOf(ActivitiesCursor.getString(ActivitiesCursor.getColumnIndex("ActDes"))),
                            ActivitiesCursor.getString(ActivitiesCursor.getColumnIndex("country")),
                            ActivitiesCursor.getFloat(ActivitiesCursor.getColumnIndex("cost")),
                            Date.parse(ActivitiesCursor.getString(ActivitiesCursor.getColumnIndex("StartDate"))),
                            Date.parse(ActivitiesCursor.getString(ActivitiesCursor.getColumnIndex("EndDate"))),
                            ActivitiesCursor.getString(ActivitiesCursor.getColumnIndex("LongDes")),
                            ActivitiesCursor.getInt(ActivitiesCursor.getColumnIndex("BusinessId"))));
                }
                if(BusinessCursor != null)
                for (BusinessCursor.moveToFirst(); !BusinessCursor.isAfterLast(); BusinessCursor.moveToNext()) {
                    businessArrayList.add(new business(
                            BusinessCursor.getString(BusinessCursor.getColumnIndex("name")),
                            Address.parse(BusinessCursor.getString(BusinessCursor.getColumnIndex("address"))),
                            BusinessCursor.getString(BusinessCursor.getColumnIndex("PhoneNumber")),
                            BusinessCursor.getString(BusinessCursor.getColumnIndex("Email")),
                            BusinessCursor.getString(BusinessCursor.getColumnIndex("Website")),
                            BusinessCursor.getInt(BusinessCursor.getColumnIndex("ID"))));
                }
                this.cancel(true);
            }

            @Override
            protected Uri doInBackground(Void... params) {
                ActivitiesCursor = c.getContentResolver().query(Uri.parse("content://com.project.arielfaridja/Activities"), null, null, null, null);
                BusinessCursor = c.getContentResolver().query(Uri.parse("content://com.project.arielfaridja/Business"), null, null, null, null);
                return null;
            }
        }.execute();
    }

    public ArrayList<business> GetAllBusinessByCountry(String country) {
        ArrayList<business> ToReturn = new ArrayList<>();
        for (business b : businessArrayList)
            if (b.getAddress().getCountry() == country)
                ToReturn.add(b);
        return ToReturn;
    }

    public ArrayList<activity> GetAllActivitiesByBusiness(int id) {
        ArrayList<activity> ToReturn = new ArrayList<>();
        for (activity a : this.activitiesArrayList)
            if (a.getBusinessId() == id)
                ToReturn.add(a);
        return ToReturn;
    }

    @Override
    public ArrayList<activity> getActivitiesArrayList() {
        return activitiesArrayList;
    }

    @Override
    public ArrayList<business> getBusinessArrayList() {
        return businessArrayList;
    }

    @Override
    public ArrayList<activity> GetAllActivitiesByCountry(String country) {
        ArrayList<activity> ToReturn = new ArrayList<>();
        for (activity a : activitiesArrayList)
            if (a.getCountry() == country)
                ToReturn.add(a);
        return ToReturn;
    }
}
/*

 */