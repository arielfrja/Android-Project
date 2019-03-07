package com.project.arielfaridja.trip.controller;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.BackEnd.OnFragmentInteractionListener;
import com.project.arielfaridja.trip.model.Entities.activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityShowFragment extends Fragment implements View.OnClickListener {

    private TextView ActCountry;
    private TextView ActDescription;
    private TextView ActCost;
    private TextView ActStartDate;
    private TextView ActEndDate;
    private TextView LongActDes;
    private Button AgencyPage;
    public activity Act = new activity();
    private OnFragmentInteractionListener mListener;


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-03-10 01:24:08 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View v) {
        ActCountry = (TextView) v.findViewById(R.id.ActCountry);
        ActDescription = (TextView) v.findViewById(R.id.ActDescription);
        ActCost = (TextView) v.findViewById(R.id.ActCost);
        ActStartDate = (TextView) v.findViewById(R.id.ActStartDate);
        ActEndDate = (TextView) v.findViewById(R.id.ActEndDate);
        LongActDes = (TextView) v.findViewById(R.id.LongActDes);
        AgencyPage = (Button) v.findViewById(R.id.AgencyPage);

        ActCountry.setText(Act.getCountry());
        ActDescription.setText(Act.getDescription().toString().replace("_", " "));
        ActCost.setText(String.valueOf(Act.getCost()));
        ActStartDate.setText(Act.getStartDate().toString());
        ActEndDate.setText(Act.getEndDate().toString());
        LongActDes.setText(Act.getLongDescription());

        AgencyPage.setOnClickListener( this );
    }

    public ActivityShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_activity_show, container, false);
        findViews(v);
        // Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Funny.ttf");
        //ActCountry.setTypeface(type);
        return v;
    }

    @Override
    public void onClick(View v) {


    }

}
