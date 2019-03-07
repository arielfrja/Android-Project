package com.project.arielfaridja.Operator.controller;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.arielfaridja.Operator.model.Backend.DataSourceChangesCheck;
import com.project.arielfaridja.R;

public class MainActivity extends Activity {

    private Intent nextPage;
    final String LoginOptions = "com.project.arielfaridja.LOGIN_TO_SETTINGS";
    static final String USER_EMAIL = "com.project.arielfaridja.USER_EMAIL";
    static final String USER_PASSWORD = "com.project.arielfaridja.USER_PASSWORD";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cont = (Button) findViewById(R.id.Cont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSharedPreferencesToIntent();
                startActivity(nextPage);
            }
        });
        startService(new Intent(MainActivity.this, DataSourceChangesCheck.class));
    }
    private void loadSharedPreferencesToIntent()
    {
        SharedPreferences userdata = getSharedPreferences("com.project.arielfaridja.userdata", 0);


        if (userdata.contains("EMAIL") && userdata.contains("PASSWORD")) {
            nextPage = new Intent(MainActivity.this, OptionsActivity.class);
            nextPage.setAction(LoginOptions);
            nextPage.putExtra(USER_EMAIL, userdata.getString("EMAIL", null));
            nextPage.putExtra(USER_PASSWORD,userdata.getString("PASSWORD", null));
        }
        else
            nextPage = new Intent(MainActivity.this, LoginActivity.class);
    }
}
