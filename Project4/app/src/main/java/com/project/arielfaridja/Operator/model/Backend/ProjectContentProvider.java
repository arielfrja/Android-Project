package com.project.arielfaridja.Operator.model.Backend;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.project.arielfaridja.Operator.model.Datasource.DataWorkFactory;
import com.project.arielfaridja.Operator.model.Datasource.FunctionsInterface;
import com.project.arielfaridja.Operator.model.Entities.ActDes;
import com.project.arielfaridja.Operator.model.Entities.Address;
import com.project.arielfaridja.Operator.model.Entities.Date;
import com.project.arielfaridja.Operator.model.Entities.activity;
import com.project.arielfaridja.Operator.model.Entities.business;
import com.project.arielfaridja.Operator.model.Entities.user;

import static android.content.ContentValues.TAG;

public class ProjectContentProvider extends ContentProvider {
    public static final String AUTHORITY = "content://com.project.arielfaridja";
    public static final Uri CONTENT_URI = Uri.parse(AUTHORITY);
    FunctionsInterface DS = DataWorkFactory.GetFunctions();

    public ProjectContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (uri.getPath().equals("/Users")) {
            return Uri.parse(uri.toString() + "/" + DS.AddUser(new user(values.getAsString("Email"), values.getAsString("Password"))));
        }
        if (uri.getPath().equals("/Business"))
            return Uri.parse(uri.toString() + "/" +
                    (DS.AddBusiness(new business(values.getAsString("name"),
                                    Address.parse(values.getAsString("Address")),
                                    values.getAsString("PhoneNumber"),
                                    values.getAsString("Email"),
                                    values.getAsString("Website")
                            )

                    )));
        if (uri.getPath().equals("/Activities"))
            DS.AddActivity(new activity(ActDes.valueOf(values.getAsString("ActDes")),
                            values.getAsString("country"),
                            values.getAsFloat("cost"),
                            Date.parse(values.getAsString("StartDate")),
                            Date.parse(values.getAsString("EndDate")),
                            values.getAsString("LongDes"),
                            values.getAsInteger("BusinessId")
                    )
            );
        return null;
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        if (uri.getPath().equals("/Business")) {
            String[] columns = new String[]{"ID", "name", "address", "PhoneNumber", "Email", "Website"};
            MatrixCursor matrixCursor = new MatrixCursor(columns);
            try {
                for (business b : DS.GetAllBusiness()) {
                    matrixCursor.addRow(new Object[]{b.getID(), b.getName(), b.getAddress().toString(), b.getPhoneNumber(), b.getEmail(), b.getWebsite()});
                }
            } catch (Exception e) {
                Log.d(TAG, "query: " + e.getMessage());
            }
            return matrixCursor;
        }
        if (uri.getPath().equals("/Users")) {
            String[] columns = new String[]{"ID", "Email", "Password"};
            MatrixCursor matrixCursor = new MatrixCursor(columns);
            try {
                for (user U : DS.GetAllUsers()) {
                    matrixCursor.addRow(new Object[]{U.getUserid(), U.getEmail(), U.getPassword()});
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return matrixCursor;
        }
        if (uri.getPath().equals("/Activities")) {
            String[] columns = new String[]{"ActDes", "country", "StartDate", "EndDate", "cost", "LongDes", "BusinessId"};
            MatrixCursor matrixCursor = new MatrixCursor(columns);
            try {
                for (activity a : DS.GetAllActivities()) {
                    matrixCursor.addRow(new Object[]{a.getDescription().toString(), a.getCountry(), a.getStartDate().toString(), a.getEndDate().toString(), a.getCost(), a.getLongDescription(), a.getBusinessId()});
                }
            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return matrixCursor;
        }
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented, Cotent provider error!!");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
