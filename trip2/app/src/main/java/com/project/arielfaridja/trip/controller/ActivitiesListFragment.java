package com.project.arielfaridja.trip.controller;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SearchView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.BackEnd.ActivitiesByCountryAdapter;
import com.project.arielfaridja.trip.model.BackEnd.AllActivitiesFromSameCountry;
import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.activity;
import com.project.arielfaridja.trip.model.BackEnd.OnFragmentInteractionListener;
import java.util.ArrayList;

public class ActivitiesListFragment extends Fragment {
    private ActivitiesByCountryAdapter ExpAdapter;
    private SearchView searchView;

    ExpandableListView ExpList;
    DataInterface DS = DataFactory.getData();
    private OnFragmentInteractionListener mListener;

    public ActivitiesListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchView = (SearchView) getActivity().findViewById(R.id.searchView);
        setSearchViewListener();
    }

    private void setSearchViewListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ExpAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activities_list, container, false);
        // Inflate the layout for this fragment
        ExpList = (ExpandableListView) v.findViewById(R.id.ActivitiesList);
        ExpAdapter = new ActivitiesByCountryAdapter(getActivity(), GroupActivitiesByCountry());
        ExpList.setAdapter(ExpAdapter);
        ExpList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                activity a = ((activity) (ExpAdapter.getChild(groupPosition, childPosition)));
                mListener.onFragmentInteraction(Uri.parse("activity://"
                        + a.getDescription().toString()/*.replace("_", " ")*/
                        + "-" + a.getCountry()
                        + "-" + a.getCost()
                        + "-" + a.getStartDate()
                        + "-" + a.getEndDate()
                        + "-" + a.getLongDescription()
                        + "-" + String.valueOf(a.getBusinessId())
                ));

                return false;
            }
        });
        return v;
    }

    private ArrayList<AllActivitiesFromSameCountry> GroupActivitiesByCountry() {
        ArrayList<AllActivitiesFromSameCountry> GroupedByCountry = new ArrayList<>();
        Boolean flag = false;
        ArrayList<String> countries = new ArrayList<>();
        for (activity a : DS.getActivitiesArrayList()) {
            for (AllActivitiesFromSameCountry ac : GroupedByCountry) {
                if (a.getCountry() == ac.getCountry()) {
                    flag = true;
                }
            }
            if(flag == false)
            {
                AllActivitiesFromSameCountry ac = new AllActivitiesFromSameCountry(a.getCountry());
                GroupedByCountry.add(ac);
            }
        }
        return GroupedByCountry;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
