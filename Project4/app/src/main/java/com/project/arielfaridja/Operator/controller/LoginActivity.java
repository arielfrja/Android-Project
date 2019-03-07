package com.project.arielfaridja.Operator.controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.arielfaridja.Operator.model.Datasource.DataWorkFactory;
import com.project.arielfaridja.Operator.model.Datasource.FunctionsInterface;
import com.project.arielfaridja.R;


import static com.project.arielfaridja.Operator.controller.MainActivity.USER_EMAIL;
import static com.project.arielfaridja.Operator.controller.MainActivity.USER_PASSWORD;

import com.project.arielfaridja.Operator.model.Backend.ProjectContentProvider;

import com.project.arielfaridja.Operator.model.Entities.user;

import java.util.ArrayList;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity {

    SharedPreferences userdata;
    SharedPreferences.Editor editor;
    FunctionsInterface DS = DataWorkFactory.GetFunctions();

    ArrayList<user> users = new ArrayList<>();
    /**
     * Id to identity READ_CONTACTS permission request.
     */

    /**
     * A dummy authentication store containing known user names and passwords.
     */

    // UI references.
    private TextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userdata = getSharedPreferences("com.project.arielfaridja.userdata", Context.MODE_PRIVATE);
        editor = userdata.edit();
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mEmailView = (TextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        GetUsersList();
        mPasswordView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                        if (id == R.id.login || id == EditorInfo.IME_NULL) {
                            attemptLogin();
                            return true;
                        }
                        return false;
                    }
                });
        //TODO: CHeck That זנביל!!


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        showProgress(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                user U = new user();
                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                    U.setEmail(cursor.getString(cursor.getColumnIndex("Email")));
                    U.setPassword(cursor.getString(cursor.getColumnIndex("Password")));
                    U.setUserid(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ID"))));
                    users.add(U);
                }
                showProgress(false);
            }

            @Override
            protected Void doInBackground(Void... params) {
                cursor = getContentResolver().query(Uri.parse(ProjectContentProvider.AUTHORITY + "/Users"), null, null, null, null);
                return null;
            }
        }.execute();
    }

    private void GetUsersList() {
        showProgress(true);
        new AsyncTask<Void, Void, String>(){


            @Override
            protected String doInBackground(Void... params) {
                try {
                    users = DS.GetAllUsers();
                    return "Good!";
                } catch (Exception e) {
                    return e.getMessage();
                }
            }
        }.execute();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            for (user u : users) {
                if (u.getEmail().toLowerCase().equals(email.toLowerCase())) {
                    // Account exists, return true if the password matches.
                    PasswordCheck(u.getPassword(), password);
                    return;
                }

            }

            AddNewUser();

        }
    }

    private void PasswordCheck(String Upassword, String password) {
        if (Upassword.equals(password)) {
            editor.putString("PASSWORD", mPasswordView.getText().toString());
            editor.putString("EMAIL", mEmailView.getText().toString().toLowerCase());
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, OptionsActivity.class);
            intent.setAction("com.project.arielfaridja.LOGIN_TO_SETTINGS");
            intent.putExtra(USER_EMAIL, userdata.getString("EMAIL", null));
            intent.putExtra(USER_PASSWORD, userdata.getString("PASSWORD", null));
            startActivity(intent);
        } else {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
        }

    }

    private boolean isEmailValid(String email) {
        return email.toLowerCase().contains("@") && email.contains(".com");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 3;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    void AddNewUser() {
        final ContentValues values = new ContentValues();
        values.put("Email", mEmailView.getText().toString());
        values.put("Password", mPasswordView.getText().toString().toLowerCase());
        showProgress(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(LoginActivity.this, "You added new user with Email:\n" + mEmailView.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, OptionsActivity.class);
                intent.setAction("com.project.arielfaridja.LOGIN_TO_SETTINGS");
                editor.putString("PASSWORD", mPasswordView.getText().toString());
                editor.putString("EMAIL", mEmailView.getText().toString().toLowerCase());
                editor.commit();
                intent.putExtra(USER_EMAIL, userdata.getString("EMAIL", null));
                intent.putExtra(USER_PASSWORD, userdata.getString("PASSWORD", null));
                startActivity(intent);
            }

            @Override
            protected Void doInBackground(Void... params) {
                getContentResolver().insert(Uri.parse(ProjectContentProvider.AUTHORITY + "/Users"), values);
                return null;
            }
        }.execute();

    }
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}

