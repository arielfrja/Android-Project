package com.project.arielfaridja.Operator.controller;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.arielfaridja.Operator.model.Entities.Date;
import com.project.arielfaridja.R;
import com.project.arielfaridja.Operator.model.Backend.ProjectContentProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import com.project.arielfaridja.Operator.model.Entities.ActDes;
import com.project.arielfaridja.Operator.model.Entities.activity;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner Sdescription;
    private EditText Cost;
    private TextView CselectText;
    private Spinner countries;
    private Spinner Business;
    private EditText Ldiscription;
    private TextView BselectText;
    private Button AddThisActivity;
    private Button StartDatePicker;
    private Button EndDatePicker;
    Cursor cursor;
    activity mActivity = new activity();


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-02-05 08:45:09 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        Sdescription = (Spinner) findViewById(R.id.Sdescription);
        Cost = (EditText) findViewById(R.id.Cost);
        CselectText = (TextView) findViewById(R.id.CselectText);
        countries = (Spinner) findViewById(R.id.countries);
        Business = (Spinner) findViewById(R.id.business);
        Ldiscription = (EditText) findViewById(R.id.Ldiscription);
        BselectText = (TextView) findViewById(R.id.BselectText);
        AddThisActivity = (Button) findViewById(R.id.AddThisActivity);
        StartDatePicker = (Button) findViewById(R.id.StartDatePicker);
        EndDatePicker = (Button) findViewById(R.id.EndDatePicker);

        AddThisActivity.setOnClickListener(this);
        StartDatePicker.setOnClickListener(this);
        EndDatePicker.setOnClickListener(this);
        SetCountries();
        SetBusiness();
        SetAcDes();
        SetChangesListener();
        mActivity.setStartDate(new Date(1, 1, 2000));
        mActivity.setEndDate(new Date(1, 1, 2000));
    }

    private void SetAcDes() {
        ArrayList<String> ActList = new ArrayList<>();
        for (ActDes A : ActDes.values()
                ) {
            ActList.add(A.name().replace("_", " "));

        }
        Collections.sort(ActList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddActivity.this, R.layout.support_simple_spinner_dropdown_item, ActList);
        Sdescription.setAdapter(adapter);
    }

    private void SetChangesListener() {
        Cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivity.setCost(Float.parseFloat(s.toString()));
            }
        });
        Ldiscription.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mActivity.setLongDescription(s.toString());
            }
        });

    }

    void SetCountries() {
        ArrayList<String> countriesList = new ArrayList<String>();
        String country;
        for (Locale loc : Locale.getAvailableLocales()) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countriesList.contains(country)) {
                countriesList.add(country);
            }
        }
        Collections.sort(countriesList, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesList);
        countries.setAdapter(adapter);
    }

    void SetDate(final Button b) {
        int day;
        int month;
        int year;
        if (b == StartDatePicker) {
            day = mActivity.getStartDate().getDay();
            month = mActivity.getStartDate().getMonth();
            year = mActivity.getStartDate().getYear();
        } else {
            day = mActivity.getEndDate().getDay();
            month = mActivity.getEndDate().getMonth();
            year = mActivity.getEndDate().getYear();
        }
        new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                if (b == findViewById(R.id.StartDatePicker))
                    mActivity.setStartDate(new Date(dayOfMonth, month, year));
                else
                    mActivity.setEndDate(new Date(dayOfMonth, month, year));
            }
        }, year, month, day).show();
        if ((mActivity.getStartDate().getYear() > mActivity.getEndDate().getYear()
                || mActivity.getStartDate().getYear() == mActivity.getEndDate().getYear()
                && mActivity.getStartDate().getMonth() > mActivity.getEndDate().getMonth()
                || mActivity.getStartDate().getYear() == mActivity.getEndDate().getYear()
                && mActivity.getStartDate().getMonth() == mActivity.getEndDate().getMonth()
                && mActivity.getStartDate().getDay() > mActivity.getEndDate().getDay())
                && (mActivity.getEndDate().getDay() != 1
                && mActivity.getEndDate().getMonth() != 1
                && mActivity.getEndDate().getYear() != 2000)) {
            Toast.makeText(this, "Starting date must be before End date", Toast.LENGTH_SHORT).show();
            SetDate(b);
        }
    }

    void SetBusiness() {
        final ArrayList<String> BusinessList = new ArrayList<String>();
        new AsyncTask<Void, Void, Uri>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Uri uri) {
                super.onPostExecute(uri);
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    BusinessList.add(cursor.getInt(cursor.getColumnIndex("ID")) + ". " + cursor.getString(cursor.getColumnIndex("name")));
                }
                Collections.sort(BusinessList);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddActivity.this, R.layout.support_simple_spinner_dropdown_item, BusinessList);
                Business.setAdapter(adapter);
            }

            @Override
            protected Uri doInBackground(Void... params) {
                cursor = getContentResolver().query(Uri.parse(ProjectContentProvider.AUTHORITY + "/Business"), null, null, null, null);
                return null;
            }
        }.execute();
        Business.setSelection(0);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        findViews();

    }

    @Override
    public void onClick(View v) {
        if (v == AddThisActivity) {
            AddThisActivity();
        }
        if (v == StartDatePicker || v == EndDatePicker)
            SetDate((Button) v);
    }

    void AddThisActivity() {
        final ContentValues values = new ContentValues();
        try {
            /***
             * check if the end date is after the start date
             */
            if (mActivity.getStartDate().getYear() > mActivity.getEndDate().getYear()
                    || mActivity.getStartDate().getYear() == mActivity.getEndDate().getYear()
                    && mActivity.getStartDate().getMonth() > mActivity.getEndDate().getMonth()
                    || mActivity.getStartDate().getYear() == mActivity.getEndDate().getYear()
                    && mActivity.getStartDate().getMonth() == mActivity.getEndDate().getMonth()
                    && mActivity.getStartDate().getDay() > mActivity.getEndDate().getDay())
                //than {
                throw new Exception("the start year must be before the end date");
        //}
            values.put("ActDes", Sdescription.getSelectedItem().toString().replace(" ", "_"));
            values.put("country", countries.getSelectedItem().toString());
            values.put("cost", mActivity.getCost());
            values.put("StartDate", mActivity.getStartDate().toString());
            values.put("EndDate", mActivity.getEndDate().toString());
            values.put("LongDes", mActivity.getLongDescription());
            values.put("BusinessId", Integer.parseInt(Business.getSelectedItem().toString().split("\\.")[0]));

            new AsyncTask<Void, Void, Uri>() {
                @Override
                protected Uri doInBackground(Void... params) {
                    Uri uri = getContentResolver().insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/Activities"), values);
                    return uri;

                }

                @Override
                protected void onPostExecute(Uri uri) {
                    super.onPostExecute(uri);
                    finish();
                }
            }.execute();
        } catch (Exception e) {
            Toast.makeText(AddActivity.this, "ERRORRRRRR!!!\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
