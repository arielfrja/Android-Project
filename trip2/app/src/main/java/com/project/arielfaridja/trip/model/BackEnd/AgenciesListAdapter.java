package com.project.arielfaridja.trip.model.BackEnd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.controller.activityExpandableListView;
import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.ExpandableItem;
import com.project.arielfaridja.trip.model.Entities.business;

import java.util.ArrayList;

/**
 * Created by finis on 2/8/2017.
 */
public class AgenciesListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ExpandableItem> businessList;
    DataInterface DS = DataFactory.getData();

    public AgenciesListAdapter(Context context, ArrayList<ExpandableItem> business_list) {
        this.context = context;
        this.businessList = business_list;
    }

    @Override
    public int getGroupCount() {
        return businessList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //return ((ExpandableItem)getGroup(groupPosition)).getActivities().size()+1;
        return 2;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return businessList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (childPosition == 0)
            return businessList.get(groupPosition).getBusiness();
        else
            return businessList.get(groupPosition).getActivities().get(childPosition - 1);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return businessList.get(groupPosition).getBusiness().getID();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        business b = ((ExpandableItem) getGroup(groupPosition)).getBusiness();
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(b.getID() + ". " + b.getName());
        return convertView;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (childPosition == 0) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.business_details_item, null);
        } else if (childPosition > 0) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.activities_list, null);
        }
        if (childPosition == 0) {
            TextView name = (TextView) convertView.findViewById(R.id.Name);
            TextView address = (TextView) convertView.findViewById(R.id.Address);
            TextView phone = (TextView) convertView.findViewById(R.id.PhoneNumber);
            TextView email = (TextView) convertView.findViewById(R.id.Email);
            TextView website = (TextView) convertView.findViewById(R.id.Website);
            name.setText(businessList.get(groupPosition).getBusiness().getName());
            address.setText(businessList.get(groupPosition).getBusiness().getAddress().toString());
            phone.setText(businessList.get(groupPosition).getBusiness().getPhoneNumber());
            email.setText(businessList.get(groupPosition).getBusiness().getEmail());
            website.setText(businessList.get(groupPosition).getBusiness().getWebsite());
        } else {
            activityExpandableListView ExpList = new activityExpandableListView(context);
            ExpList.setAdapter(new ActivitiesAdapter(context, DS.GetAllActivitiesByBusiness(businessList.get(groupPosition).getBusiness().getID())));
            return ExpList;
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
