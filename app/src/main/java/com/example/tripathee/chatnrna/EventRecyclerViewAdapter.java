package com.example.tripathee.chatnrna;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tripathee on 6/19/2016.
 */
public class EventRecyclerViewAdapter extends RecyclerView.Adapter<EventRecyclerViewAdapter.RecyclerViewHolders> {
    private List<ItemObject> itemList;
    private Context context;
    public EventRecyclerViewAdapter(Context context, List<ItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView eventTitle;
        public TextView content;
        public TextView start_time, end_time, registered;
        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            eventTitle = (TextView)itemView.findViewById(R.id.event_title);
            content = (TextView)itemView.findViewById(R.id.content);
            start_time = (TextView)itemView.findViewById(R.id.start_time);
            end_time = (TextView)itemView.findViewById(R.id.end_time);
            registered = (TextView)itemView.findViewById(R.id.registered);
        }
        @Override
        public void onClick(View view) {
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.eventTitle.setText("Event Title: " + itemList.get(position).getTitle());
        holder.content.setText("Content: " + itemList.get(position).getContent());
        holder.start_time.setText("Start Time: " + itemList.get(position).getEvent_stdate());
        holder.end_time.setText("End time: " + itemList.get(position).getEvent_endate());
        holder.registered.setText("Registered: " + itemList.get(position).getRegistered());
    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
