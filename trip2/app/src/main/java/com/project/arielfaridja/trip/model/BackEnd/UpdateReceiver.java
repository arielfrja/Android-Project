package com.project.arielfaridja.trip.model.BackEnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.project.arielfaridja.trip.model.DataSource.DataFactory;
import com.project.arielfaridja.trip.model.DataSource.DataInterface;

public class UpdateReceiver extends BroadcastReceiver {
	DataInterface DS = DataFactory.getData();

	public UpdateReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO: This method is called when the BroadcastReceiver is receiving
		// an Intent broadcast.
		if(intent.getAction() == "com.project.arielfaridja.DATA_UPDATED")
			DS.GetAllDataFromDataBase(context);
		//DS.GetAllBusinessFromDataBase(context);
	}
}
