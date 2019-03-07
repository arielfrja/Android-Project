package com.project.arielfaridja.trip.controller;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by Harel on 13/02/2017.
 */

public class activityExpandableListView extends ExpandableListView {
    int intGroupPosition;
    int intChildPosition;
    int intGroupId;
    public activityExpandableListView(Context context) {
        super(context);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(960, MeasureSpec.AT_MOST);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(600, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
