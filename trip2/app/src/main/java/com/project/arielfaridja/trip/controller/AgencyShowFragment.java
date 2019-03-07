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
import android.widget.Button;
import android.widget.TextView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.Entities.Address;
import com.project.arielfaridja.trip.model.Entities.business;
import com.project.arielfaridja.trip.model.BackEnd.OnFragmentInteractionListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgencyShowFragment extends Fragment implements View.OnClickListener {

    business Business = new business();
    private TextView name;
    private TextView address;
    private TextView phone;
    private TextView email;
    private TextView website;
    private Button ConAgency;
    private OnFragmentInteractionListener mListener;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-03-13 15:04:56 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View v) {
        name = (TextView)v.findViewById( R.id.name );
        address = (TextView)v.findViewById( R.id.address );
        phone = (TextView)v.findViewById( R.id.phone );
        email = (TextView)v.findViewById( R.id.email );
        website = (TextView)v.findViewById( R.id.website );
        ConAgency = (Button)v.findViewById( R.id.ConAgency );

        ConAgency.setOnClickListener( this );
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String webAddress);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2017-03-13 15:04:56 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == ConAgency ) {
            final Intent intent = new Intent();

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
                    intent.setData(Uri.parse("tel:" + Business.getPhoneNumber()));
                    startActivity(intent);
                    dialog.hide();

                }
            });
            Email.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            intent.setData(Uri.parse("mailto:" + Business.getEmail()));
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
                            mListener.onFragmentInteraction(Business.getWebsite());
                        }
                    }

            );
            Map.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Address a = Business.getAddress();
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
        }
    }

    public AgencyShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agency_show, container, false);
        findViews(v);
        return v;
    }

}
