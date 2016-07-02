package com.example.tripathee.chatnrna;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tripathee on 6/4/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private List<Person> personList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, time;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            time = (TextView) view.findViewById(R.id.time);
        }
    }
    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contact_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Person person = personList.get(position);
        holder.name.setText(person.getName());
        holder.email.setText(person.getEmail());
        holder.time.setText(person.getTime());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
