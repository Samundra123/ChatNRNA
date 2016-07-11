package com.example.tripathee.chatnrna;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Tripathee on 6/4/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "nameKey";
    public static final String Email = "emailKey";
    public static final String Password = "passKey";
    public static final String App_id ="save_id";
    public static final String Pass_App_Id ="pass_app_id";
    private static final int REQUEST_SIGNUP = 0;
    final String[] abc = new String[5];
    String app_user_id_;
    String notimes;
    String email;
    String password;
    SharedPreferences sharedpreferences;
    Integer count=0;

    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.btn_login) Button _loginButton;
    @BindView(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       // ButterKnife.bind(this);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);

        //create sharedpreferencees instance
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        System.out.println( "oooo "+sharedpreferences.getString("save_user_id", null));

        if(sharedpreferences.getString("save_user_id", null) != null || sharedpreferences.getString(Email, null) != null){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if(isNetworkAvailable() == true) {
                    login();
                }
                else {
                   Toast.makeText(LoginActivity.this, "Login failed, Please check your connection", Toast.LENGTH_LONG).show();
                }
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        //if the value of email is not null, then go to our MainActivity class
        //if this clause is executed then, Main Activity is launched directly

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //when user types the value of email and password it saves it into email and password
        email = _emailText.getText().toString();
        password = _passwordText.getText().toString();


        //this function parses the json and get the required response
        requestJsonObject();
        System.out.println("Check one" +app_user_id_);

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed

                        //this checks the value of response
                        //if true then save it to sharedpreferences for future use
                        //then open logins success
                        try {
                            if (!app_user_id_.equals("false")) {

                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString(Email, email);
                                editor.putString(Password, password);
                                editor.commit();
                                //editor.putString(App_id, app_user_id_);

                                if (!notimes.equals("a")) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    intent.putExtra("app_user", app_user_id_);
                                    startActivity(intent);
                                    finish();
                                    System.out.println("Check");
                                } else {
                                    onLoginSuccess();
                                }
                            }
                            //if check is false then it is executed
                            else {
                                onLoginFailed();
                            }
                        } catch (Exception e){
                            e.getMessage();
                        }
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }

                }, 3000);
    }

    public void requestJsonObject() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://nrna.org.np/nrna_app/app_user/check_user/" + email + "/" + password;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                try {
                    JSONArray object = new JSONArray(response);
                    //JSONArray Jarray = object.getJSONArray("app_user_id");


                        JSONObject Jasonobject = object.getJSONObject(0);
                        app_user_id_ = Jasonobject.getString("app_user_id");
                        notimes = Jasonobject.getString("notimes");
                        System.out.println("app" +app_user_id_ +notimes);

                    //saves the value of the abc

                    //String site = jsonResponse.getString("site")

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error " + error.getMessage());
            }
        });
        int socketTimeout = 30000;
        stringRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        socketTimeout,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        intent.putExtra(Pass_App_Id, app_user_id_);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
