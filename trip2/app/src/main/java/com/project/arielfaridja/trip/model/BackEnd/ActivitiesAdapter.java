package com.project.arielfaridja.trip.model.BackEnd;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.project.arielfaridja.trip.R;
import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;
import com.project.arielfaridja.trip.model.Entities.activity;

import java.util.ArrayList;

/**
 * Created by Ariel Faridja on 12/02/2017.
 */

public class ActivitiesAdapter extends BaseExpandableListAdapter implements
        Filterable {
    private Context context;
    private ArrayList<activity> activitiesList;
    private ArrayList<activity> OriginalActivitiesList;

    public ActivitiesAdapter(Context context, ArrayList<activity> activities_List) {
        this.context = context;
        this.activitiesList = activities_List;
        OriginalActivitiesList = activities_List;
    }

    @Override
    public int getGroupCount() {
        return activitiesList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return activitiesList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return activitiesList.get(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.group_item, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.group_name);
        tv.setText(activitiesList.get(groupPosition).getCountry());
        tv.setTextColor(Color.BLUE);
        tv.setTextSize(18);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.activity_item, null);
        }
        TextView ActCountry = (TextView) convertView.findViewById(R.id.ActCountry);
        TextView ActDescription = (TextView) convertView.findViewById(R.id.ActDescription);
        TextView ActCost = (TextView) convertView.findViewById(R.id.ActCost);
        TextView ActStartDate = (TextView) convertView.findViewById(R.id.ActStartDate);
        TextView ActEndDate = (TextView) convertView.findViewById(R.id.ActEndDate);
        ActCountry.setText(activitiesList.get(groupPosition).getCountry());
        ActDescription.setText(activitiesList.get(groupPosition).getDescription().toString().replace("_", " "));
        ActCost.setText("Cost: " + String.valueOf(activitiesList.get(groupPosition).getCost()));
        ActStartDate.setText("Starting date: " + activitiesList.get(groupPosition).getStartDate().toString());
        ActEndDate.setText("End date: " + activitiesList.get(groupPosition).getEndDate().toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<activity> resultList = new ArrayList<>();
                for (activity a : OriginalActivitiesList
                        ) {
                    if (a.getCountry().toLowerCase().startsWith(constraint.toString().toLowerCase()))
                        resultList.add(a);
                }
                results.values = resultList;
                results.count = resultList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                    activitiesList = (ArrayList<activity>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
