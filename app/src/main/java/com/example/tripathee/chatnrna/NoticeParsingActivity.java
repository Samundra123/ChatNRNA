package com.example.tripathee.chatnrna;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Waters on 7/7/2016.
 */
public class NoticeParsingActivity extends Fragment {
    private final String TAG = "NoticeParsingActivity";
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private NoticeRecyclerViewAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.recycle_events, container, false);
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recycler_view);
        //recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        requestJsonObject();

        return rootView;
    }
    private void requestJsonObject(){
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url ="http://nrna.org.np/nrna_app/notice/nt";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Response " + response);
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                List<NoticeObject> posts = new ArrayList<NoticeObject>();
                posts = Arrays.asList(mGson.fromJson(response, NoticeObject[].class));
                adapter = new NoticeRecyclerViewAdapter(getActivity(), posts);
                recyclerView.setAdapter(adapter);
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Error " + error.getMessage());
            }
        });
        queue.add(stringRequest);
    }

    }


