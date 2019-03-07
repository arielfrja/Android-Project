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
import com.project.arielfaridja.trip.model.Entities.activity;

import java.util.ArrayList;

/**
 * Created by Ariel Faridja on 02/03/2017.
 */

public class ActivitiesByCountryAdapter extends BaseExpandableListAdapter implements Filterable {
    ArrayList<AllActivitiesFromSameCountry> list;
    private ArrayList<AllActivitiesFromSameCountry> OriginalList;
    private Context context;

    public ActivitiesByCountryAdapter(Context context, ArrayList<AllActivitiesFromSameCountry> list) {
        this.list = list;
        OriginalList = list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).activities.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).activities.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition*10 + childPosition;
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
        tv.setText(list.get(groupPosition).getCountry());
        tv.setTextColor(Color.YELLOW);
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
        ActCountry.setText(list.get(groupPosition).activities.get(childPosition).getCountry());
        ActDescription.setText(list.get(groupPosition).activities.get(childPosition).getDescription().toString().replace("_", " "));
        ActCost.setText("Cost: " + String.valueOf(list.get(groupPosition).activities.get(childPosition).getCost()));
        ActStartDate.setText("Starting date: " + list.get(groupPosition).activities.get(childPosition).getStartDate().toString());
        ActEndDate.setText("End date: " + list.get(groupPosition).activities.get(childPosition).getEndDate().toString());
        return convertView;    }

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
                ArrayList<AllActivitiesFromSameCountry> resultList = new ArrayList<>();
                for (AllActivitiesFromSameCountry a : OriginalList
                        ) {
                    if (a.getCountry().toLowerCase().startsWith(constraint.toString().toLowerCase()))
                        resultList.add(a);
                }
                results.values = resultList;
                results.count = resultList.size();
                return results;            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list = (ArrayList<AllActivitiesFromSameCountry>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
