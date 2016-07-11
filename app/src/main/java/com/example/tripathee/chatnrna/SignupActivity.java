package com.example.tripathee.chatnrna;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;

/**
 * Created by Tripathee on 6/4/2016.
 */
public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    public static final String MyPREFERENCES = "MyPrefs";

    public static final String Pass_App_Id ="pass_app_id";
    final String[] abc = new String[5];
    SharedPreferences sharedpreferences;

    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_email) EditText _add1Text;
    @BindView(R.id.input_password) EditText _add2Text;

    @BindView(R.id.input_password) EditText _phoneText;
    @BindView(R.id.btn_signup)
    Button _signupButton;

    String name;
    String add1;
    String add2 ;
    String phone;

    String app_user_id;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //ButterKnife.bind(this);
        _nameText = (EditText) findViewById(R.id.input_name);
        _add1Text = (EditText) findViewById(R.id.input_add1);
        _add2Text = (EditText) findViewById(R.id.input_add2);
        _phoneText = (EditText) findViewById(R.id.input_phone);
        _signupButton = (Button) findViewById(R.id.btn_signup);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        System.out.println("Name data" +name +add1 +add2 +phone);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            app_user_id = extras.getString(Pass_App_Id);
            System.out.println("Check me" +app_user_id);
        }

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        name = _nameText.getText().toString();
        add1 = _add1Text.getText().toString();
        add2 = _add2Text.getText().toString();
        phone = _phoneText.getText().toString();

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating Account...");

        progressDialog.show();


        requestJsonObject();


        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        if(!abc[0].equals("true")) {
                            Toast.makeText(SignupActivity.this, "Updating Record failed, Please provide info once again",Toast.LENGTH_LONG).show();

                        }
                        else{
                            onSignupSuccess();
                        }
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public String[] requestJsonObject() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://nrna.org.np/nrna_app/app_user/set_user_data/" +app_user_id +"/" + name + "/" + add1 + "/" + add2 +"/" + phone;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                try {
                    JSONArray object = new JSONArray(response);

                        JSONObject Jasonobject = object.getJSONObject(0);
                        abc[0] = Jasonobject.getString("reply");
                        Integer app_id_check = Jasonobject.getInt("userid");
                        System.out.println("app" +abc[0] +"check" + app_id_check);

                        //saves the value of the abc
                    //String site = jsonResponse.getString("site")

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //GsonBuilder builder = new GsonBuilder();
                //Gson mGson = builder.create();
                //List<LoginData> posts = new ArrayList<LoginData>();
                //posts = Arrays.asList(mGson.fromJson(response, LoginData[].class));

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
        return abc;
    }


    public void onSignupSuccess() {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("save_user_id", app_user_id);
        editor.commit();
        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
        intent.putExtra("sign_user_id", app_user_id);
        startActivity(intent);
       /* _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);*/
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String add1 = _add1Text.getText().toString();
        String add2 = _add2Text.getText().toString();
        String phone = _phoneText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (add1.isEmpty() || add1.length() < 2) {
            _add1Text.setError("enter a valid address");
            valid = false;
        } else {
            _add1Text.setError(null);
        }

        if (add2.isEmpty() || add2.length() < 2) {
            _add2Text.setError("enter a valid address ");
            valid = false;
        } else {
            _add2Text.setError(null);
        }

        if(phone.isEmpty() || phone.length() <7){
            _phoneText.setError("enter Valid Phone number");
            valid = false;
        } else {
            _phoneText.setError(null);
        }

        return valid;
    }
}
