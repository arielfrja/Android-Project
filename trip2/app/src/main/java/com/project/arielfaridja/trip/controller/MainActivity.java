package com.project.arielfaridja.trip.controller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.BackEnd.OnFragmentInteractionListener;
import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.ActDes;
import com.project.arielfaridja.trip.model.Entities.Date;
import com.project.arielfaridja.trip.model.Entities.activity;
import com.project.arielfaridja.trip.model.Entities.business;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnFragmentInteractionListener {
    DataInterface DS = DataFactory.getData();
    RelativeLayout content_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        content_main = (RelativeLayout) findViewById(R.id.content_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DS.GetAllDataFromDataBase(this);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (fm.getBackStackEntryCount() > 0) {
                fm.popBackStack();
            } else
                super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentManager fragmentMangar = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentMangar.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_agencies) {
            Fragment fragment = new AgenciesListFragment();
            fragmentTransaction.replace(R.id.FragmentContainer, fragment).addToBackStack(null).commit();
            content_main.setBackgroundColor(Color.YELLOW);
        } else if (id == R.id.nav_trips) {
            Fragment fragment = new ActivitiesListFragment();
            fragmentTransaction.replace(R.id.FragmentContainer, fragment).addToBackStack(null).commit();
            content_main.setBackgroundColor(Color.RED);

        } else if (id == R.id.nav_exit) {
            finish();
            android.os.Process.killProcess(android.os.Process.myPid());

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String WebAddress) {
        FragmentManager fragmentMangar = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentMangar.beginTransaction();
        Fragment fragment = new WebsiteView();
        ((WebsiteView) fragment).url = "http://" + WebAddress;
        fragmentTransaction.replace(R.id.FragmentContainer, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        FragmentManager fragmentMangar = getFragmentManager();
        Fragment fragment;
        FragmentTransaction fragmentTransaction = fragmentMangar.beginTransaction();

        if (uri.toString().startsWith("activity://")) {
            activity Act;
            String data[] = uri.toString().replace("activity://", "").split("-");
            Act = new activity(ActDes.valueOf(data[0]),
                    data[1],
                    Float.valueOf(data[2]),
                    Date.parse(data[3]),
                    Date.parse(data[4]),
                    data[5],
                    Integer.valueOf(data[6]));
            Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
            fragment = new ActivityShowFragment();
            {
                ((ActivityShowFragment) fragment).Act = Act;
            }
            fragmentTransaction.replace(R.id.FragmentContainer, fragment).addToBackStack(null).commit();
        }

        if (uri.toString().startsWith("agency://")) {
            business Business = new business();
            for (business b : DS.getBusinessArrayList()) {
                if (Integer.valueOf(uri.toString().replace("agency://", "")) == b.getID())
                    Business.setEmail(b.getEmail());
                Business.setWebsite(b.getWebsite());
                Business.setName(b.getName());
                Business.setID(b.getID());
                Business.setPhoneNumber(b.getPhoneNumber());
                Business.setAddress(b.getAddress());
                fragment = new AgencyShowFragment();
                {
                    ((AgencyShowFragment) fragment).Business = Business;
                }
                fragmentTransaction.replace(R.id.FragmentContainer, fragment).addToBackStack(null).commit();
            }
        }

    }
}



