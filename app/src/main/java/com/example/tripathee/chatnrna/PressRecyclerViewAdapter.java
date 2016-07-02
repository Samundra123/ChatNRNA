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
public class PressRecyclerViewAdapter extends RecyclerView.Adapter<PressRecyclerViewAdapter.RecyclerViewHolders> {
    private List<PressItemObject> itemList;
    private Context context;
    public PressRecyclerViewAdapter(Context context, List<PressItemObject> itemList) {
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
            eventTitle = (TextView)itemView.findViewById(R.id.press_title);
            /*content = (TextView)itemView.findViewById(R.id.content);

            start_time = (TextView)itemView.findViewById(R.id.press_year);
            end_time = (TextView)itemView.findViewById(R.id.pdf);*/
            //registered = (TextView)itemView.findViewById(R.id.id);
        }
        @Override
        public void onClick(View view) {
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_press_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {


        holder.eventTitle.setText(itemList.get(position).getTitle());
        //holder.content.setText("Content: " + Jsoup.parse(itemList.get(position).getRegistered()).text());
        // holder.start_time.setText("Start Time: " + itemList.get(position).getCreated());
        //holder.end_time.setText("End time: " + itemList.get(position).getImage());
        //holder.registered.setText("Registered: " + itemList.get(itemList.get(position).getId()).getId());
    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}


