package com.example.tripathee.chatnrna;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    TextView location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        location = (TextView) findViewById(R.id.location);

        //Get the value from login activity and set the text of app_user_id
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            location.setText(extras.getString("record"));
        }
        /*LoginActivity check_login = new LoginActivity();

        if(check_login.requestJsonObject() !=null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }*/

        final String URL = "http://nrna.org.np/nrna_app/app_user/set_location/";
        if(isNetworkAvailable() ==true) {
            RequestQueue queue = Volley.newRequestQueue(this);

            StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+getUserCountry(this), null, null);
// Add the request to the RequestQueue.
            queue.add(stringRequest);


// add the request object to the queue to be executed
           // ApplicationController.getInstance().addToRequestQueue(req);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getUserCountry(Context context) {
        try {
            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //final String simCountry = tm.getSimCountryIso();
            /*if (simCountry != null && simCountry.length() == 2) { // SIM country code is available
                return simCountry.toLowerCase(Locale.US);
            }*/
            if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) { // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) { // network country code is available
                    return networkCountry.toLowerCase(Locale.US);
                }
            }
        }
        catch (Exception e) { }
        return null;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment= null;

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_contact) {
            fragment =getSupportFragmentManager().findFragmentByTag("ContactActivity");
            if(fragment ==null){
                fragment = new ContactActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"ContactActivity").commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_news) {
            fragment =getSupportFragmentManager().findFragmentByTag("NewsActivity");
            if(fragment ==null){
                fragment = new NewsActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"NewsActivity").commit();
            drawer.closeDrawer(GravityCompat.START);

        }

        else if (id == R.id.nav_events) {

            fragment =getSupportFragmentManager().findFragmentByTag("EventParsingActivity");
            if(fragment ==null){
                fragment = new EventParsingActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"EventParsingActivity").commit();
            drawer.closeDrawer(GravityCompat.START);

        }
        else if (id == R.id.nav_newsletter) {

            fragment =getSupportFragmentManager().findFragmentByTag("NewsLetterParsingActivity");
            if(fragment ==null){
                fragment = new NewsLetterParsingActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"NewsLetterParsingActivity").commit();
            drawer.closeDrawer(GravityCompat.START);

        } else if (id == R.id.nav_press) {
            fragment =getSupportFragmentManager().findFragmentByTag("PressParsingActivity");
            if(fragment ==null){
                fragment = new PressParsingActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"PressParsingActivity").commit();
            drawer.closeDrawer(GravityCompat.START);


        } else if (id == R.id.nav_notice) {
            fragment =getSupportFragmentManager().findFragmentByTag("NoticeParsingActivity");
            if(fragment ==null){
                fragment = new NoticeParsingActivity();
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.home,fragment,"NoticeParsingActivity").commit();
            drawer.closeDrawer(GravityCompat.START);

        }

        return true;
    }
}
