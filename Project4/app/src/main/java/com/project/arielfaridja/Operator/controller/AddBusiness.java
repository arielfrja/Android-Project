package com.project.arielfaridja.Operator.controller;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.project.arielfaridja.Operator.model.Backend.ProjectContentProvider;
import com.project.arielfaridja.Operator.model.Entities.Address;

import com.project.arielfaridja.R;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.ConstValue;
public class AddBusiness extends Activity implements View.OnClickListener {
    Intent FirstIntent = getIntent();
    Intent SecIntent = null;
    private EditText Name;
    private EditText Email;
    private EditText Phone;
    private Button Address;
    private Button AddThisBusiness;
    private EditText Website;
    private Address address;
    business Business = new business();
  //  private ContentResolver resolver = getContentResolver();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-01-04 23:06:24 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        Name = (EditText) findViewById(R.id.Name);
        Email = (EditText) findViewById(R.id.Email);
        Phone = (EditText) findViewById(R.id.Phone);
        Address = (Button) findViewById(R.id.Address);
        Website = (EditText) findViewById(R.id.Website);
        AddThisBusiness = (Button) findViewById(R.id.AddThisBusiness);
        Address.setOnClickListener(this);
        AddThisBusiness.setOnClickListener(this);
        Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Business.setName(s.toString());
            }
        });
        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Business.setEmail(s.toString());
            }
        });
        Phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Business.setPhoneNumber(s.toString());
            }
        });
        Website.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Business.setWebsite(s.toString());
            }
        });
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-01-04 23:06:24 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == Address) {
            Intent intent = new Intent(AddBusiness.this, InsertAddress.class);
            startActivityForResult(intent, 1);
            // Handle clicks for Address
        }
        if (v == AddThisBusiness) {
            AddTheBusiness();
        }
    }

    private void AddTheBusiness() {
        final ContentValues values = new ContentValues();
        values.put("name", Business.getName());
        values.put("Address", Business.getAddress().toString());

        values.put("Email", Business.getEmail());
        values.put("PhoneNumber", Business.getPhoneNumber());
        values.put("Website", Business.getWebsite());
        new AsyncTask<Void, Void, Uri>() {
            @Override
            protected Uri doInBackground(Void... params) {
                Uri uri = getContentResolver().insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/Business"), values);
                return uri;

            }

            @Override
            protected void onPostExecute(Uri uri) {
                super.onPostExecute(uri);
                finish();
            }
        }.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Business.setAddress((Address) data.getSerializableExtra(ConstValue.ADDRESS_KEY));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);
        findViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
