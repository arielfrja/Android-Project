package com.project.arielfaridja.Operator.controller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.arielfaridja.R;

import static com.project.arielfaridja.Operator.controller.MainActivity.USER_EMAIL;

public class OptionsActivity extends Activity implements View.OnClickListener  {

    Intent intent;
    private Button AddActivity;
    private Button AddBusiness;
    private Button LogOut;
    private TextView HTE;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2016-12-28 00:45:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        AddActivity = (Button)findViewById( R.id.AddActivity );
        AddBusiness = (Button)findViewById( R.id.AddBusiness );
        LogOut = (Button)findViewById(R.id.LogOut);
        HTE = (TextView)findViewById( R.id.HTE );
        AddActivity.setOnClickListener( this );
        AddBusiness.setOnClickListener( this );
        LogOut.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2016-12-28 00:45:34 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v)
    {
        if ( v == AddActivity )
        {
            intent.setComponent(new ComponentName(OptionsActivity.this,AddActivity.class));
            startActivity(intent);
            // Handle clicks for AddActivity
        }
        else if ( v == AddBusiness )
        {
            intent = new Intent(OptionsActivity.this,AddBusiness.class);
            startActivity(intent);
            // Handle clicks for AddBusiness
        }
        else if(v == LogOut)
        {
            LogingOut();
        }
    }
    private void LogingOut()
    {
        SharedPreferences userdata = getSharedPreferences("com.project.arielfaridja.userdata", 0);
        SharedPreferences.Editor editor = userdata.edit();
        editor.clear();
        editor.commit();
        Intent intent = new Intent(OptionsActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_options);
        findViews();
        HTE.setText("Hello to " + intent.getStringExtra(USER_EMAIL));
    }
}
