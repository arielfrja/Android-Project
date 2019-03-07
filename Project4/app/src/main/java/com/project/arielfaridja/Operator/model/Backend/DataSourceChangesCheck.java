package com.project.arielfaridja.Operator.model.Backend;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.project.arielfaridja.Operator.model.Datasource.DataWorkFactory;
import com.project.arielfaridja.Operator.model.Datasource.FunctionsInterface;

import static android.content.ContentValues.TAG;

public class DataSourceChangesCheck extends Service {
    FunctionsInterface DS = DataWorkFactory.GetFunctions();

    public DataSourceChangesCheck() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(DataSourceChangesCheck.this, "The service start running", Toast.LENGTH_LONG).show();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(10000);
                        if (DS.IsChangedSomthing()) {
                            Intent intent1 = new Intent("com.project.arielfaridja.DATA_UPDATED");
                            DataSourceChangesCheck.this.sendBroadcast(intent1);
                        }

                    }
                    catch (Exception e)
                    {
                        Log.d(TAG, "run: ");
                    }
                }
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
