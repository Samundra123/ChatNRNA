package com.example.tripathee.chatnrna;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Waters on 7/7/2016.
 */
public class NoticeRecyclerViewAdapter extends RecyclerView.Adapter<NoticeRecyclerViewAdapter.RecyclerViewHolders> {
    private List<NoticeObject> itemList;
    private Context context;
    public NoticeRecyclerViewAdapter(Context context, List<NoticeObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView notice_title, notice_description;
        public TextView notice_date;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            notice_title = (TextView)itemView.findViewById(R.id.notice_title);
            notice_date = (TextView)itemView.findViewById(R.id.notice_date);
            notice_description =(TextView)itemView.findViewById(R.id.notice_description);

        }
        @Override
        public void onClick(View view) {
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_notice_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {

        holder.notice_title.setText(itemList.get(position).getNotice_title());
        holder.notice_date.setText(itemList.get(position).getNdate());
        holder.notice_description.setText(itemList.get(position).getNotice_description());
    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
