package com.project.arielfaridja.trip.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.BackEnd.AgenciesListAdapter;
import com.project.arielfaridja.trip.model.BackEnd.OnFragmentInteractionListener;
import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.Address;
import com.project.arielfaridja.trip.model.Entities.ExpandableItem;
import com.project.arielfaridja.trip.model.Entities.activity;
import com.project.arielfaridja.trip.model.Entities.business;

import java.util.ArrayList;


public class AgenciesListFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    DataInterface DS = DataFactory.getData();
    private AgenciesListAdapter ExpAdapter;
    private ArrayList<ExpandableItem> ExpListItems = new ArrayList<>();
    private ExpandableListView ExpandList;
    // TODO: Rename and change types of parameters

    private OnFragmentInteractionListener mListener;

    public AgenciesListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (business b : DS.getBusinessArrayList()
                ) {
            ExpListItems.add(new ExpandableItem(b));

        }
        ExpAdapter = new AgenciesListAdapter(getActivity(), ExpListItems);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agencies_list, container, false);
        // Inflate the layout for this fragment
        ExpandList = (ExpandableListView) v.findViewById(R.id.AgenciesDetailedList);
        ExpandList.setAdapter(ExpAdapter);
        SetListener();
        return v;
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    void SetListener() {
        ExpandList.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                final int groupPosition, int childPosition, long id) {
                        if (childPosition == 0) {
                            final Intent intent = new Intent();
                            int group_name = ExpListItems.get(groupPosition).getBusiness().getID();

                            final Dialog dialog = new Dialog(getActivity());
                            dialog.setContentView(R.layout.agency_options_dialog);
                            dialog.setTitle("choose operation");
                            Button Call = (Button) dialog.findViewById(R.id.Call);
                            Button Email = (Button) dialog.findViewById(R.id.Email);
                            Button Website = (Button) dialog.findViewById(R.id.Website);
                            Button Map = (Button) dialog.findViewById(R.id.Map);
                            // if button is clicked, close the custom dialog
                            Call.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    intent.setAction(Intent.ACTION_DIAL);
                                    intent.setData(Uri.parse("tel:" + ExpListItems.get(groupPosition).getBusiness().getPhoneNumber()));
                                    startActivity(intent);
                                    dialog.hide();

                                }
                            });
                            Email.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            intent.setData(Uri.parse("mailto:" + ExpListItems.get(groupPosition).getBusiness().getEmail()));
                                            intent.setAction(Intent.ACTION_SENDTO);
                                            startActivity(intent);
                                            dialog.hide();
                                        }
                                    }

                            );
                            Website.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.hide();
                                            mListener.onFragmentInteraction(ExpListItems.get(groupPosition).getBusiness().getWebsite());
                                        }
                                    }

                            );
                            Map.setOnClickListener(
                                    new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Address a = ExpListItems.get(groupPosition).getBusiness().getAddress();
                                            intent.setAction(Intent.ACTION_VIEW);
                                            intent.setPackage("com.google.android.apps.maps");
                                            intent.setData(Uri.parse("geo:0,0?q=" + a.getStreet().replace(" ", "+")
                                                    + "+" + a.getNumber() + ", "
                                                    + a.getCity().replace(" ", "+") + ", "
                                                    + a.getCountry().replace(" ", "+")));
                                            startActivity(intent);
                                            dialog.hide();
                                        }
                                    });
                            dialog.show();
                            return false;
                        }
                        else
                        {

                        }
                        return false;
                    }
                }

        );
        ExpandList.setOnItemSelectedListener(this);
    }
}
