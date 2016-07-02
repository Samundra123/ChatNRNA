package com.example.tripathee.chatnrna;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tripathee on 6/5/2016.
 */
public class ContactActivity extends Fragment {
    public ContactActivity(){}

    private List<Person> personList = new ArrayList<Person>();
    private RecyclerView recyclerView;
    private PersonAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.recycle_contact_list, container, false);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)  rootView.findViewById(R.id.recycler_view);

        mAdapter = new PersonAdapter(personList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
       // recyclerView.addOnItemTouchListener(new RecyclerItemTouchListener(getContext(), new Recyclee));

        prepareMovieData();

        return rootView;
    }



    private void prepareMovieData() {
        Person person = new Person("Gaurav Tripathee", "abc@gmail.com", "20:15");
        personList.add(person);

        person = new Person("Nepal Nepal", "def@gmail.com", "20:15");
        personList.add(person);

        person = new Person("Earth Earth", "abc@gmail.com", "22:15");
        personList.add(person);

        person = new Person("Gaurishankhar", "gauri@gmail.com", "2:10");
        personList.add(person);

        person = new Person("Annapurna", "annapurna@gmail.com", "3:34");
        personList.add(person);

        person = new Person("Mount everest", "everest@gmail.com", "6:15");
        personList.add(person);

        person = new Person("Dhaulagiri", "dhaulagiri@gmail.com", "4:15");
        personList.add(person);

        person = new Person("Ganesh", "ganesh@gmail.com", "22:20");
        personList.add(person);

        person = new Person("Manslu", "manaslu@gmail.com", "23:15");
        personList.add(person);

        person = new Person("Api", "api@gmail.com", "1:00");
        personList.add(person);

        mAdapter.notifyDataSetChanged();
    }
}
