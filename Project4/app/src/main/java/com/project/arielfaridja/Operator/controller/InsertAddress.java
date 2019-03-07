package com.project.arielfaridja.Operator.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.project.arielfaridja.R;
import com.project.arielfaridja.Operator.model.Entities.Address;
import com.project.arielfaridja.Operator.model.Entities.ConstValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class InsertAddress extends Activity implements View.OnClickListener {
    Intent GoBack = new Intent();
    Address address = new Address();
    private TextView textViewCountry;
    private TextView textViewCity;
    private TextView textViewStreet;
    private TextView textViewNumber;
    private EditText City;
    private EditText Street;
    private EditText Number;
    private Button button;
    private Spinner Country;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-01-04 22:46:25 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        /*textViewCountry = (TextView)findViewById( R.id.textViewCountry );
        textViewCity = (TextView)findViewById( R.id.textViewCity );
        textViewStreet = (TextView)findViewById( R.id.textViewStreet );
        textViewNumber = (TextView)findViewById( R.id.textViewNumber );*/
        City = (EditText) findViewById(R.id.City);
        Street = (EditText) findViewById(R.id.Street);
        Number = (EditText) findViewById(R.id.Number);
        button = (Button) findViewById(R.id.button);
        Country = (Spinner) findViewById(R.id.Country);
        button.setOnClickListener(this);
        SetCountries();
    }
    void SetCountries() {
        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countriesList = new ArrayList<String>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countriesList.contains(country)) {
                countriesList.add(country);
            }
        }
        Collections.sort(countriesList, String.CASE_INSENSITIVE_ORDER);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countriesList);
        Country.setAdapter(adapter);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-01-04 22:46:25 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == button) {
            try {
                address.setCountry(Country.getSelectedItem().toString());
                address.setCity(City.getText().toString());
                address.setStreet(Street.getText().toString());
                address.setNumber(Integer.parseInt(Number.getText().toString()));
                GoBack.putExtra(ConstValue.ADDRESS_KEY, address);
                setResult(1, GoBack);
                finish();
            }
            catch (Exception e)
            {
                Toast.makeText(InsertAddress.this, "Input problem, try again", Toast.LENGTH_LONG).show();
            }
            // Handle clicks for button
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_insert_address);
        findViews();

    }
}
