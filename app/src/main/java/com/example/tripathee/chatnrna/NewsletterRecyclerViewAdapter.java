package com.example.tripathee.chatnrna;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InterfaceAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by Tripathee on 6/19/2016.
 */
public class NewsletterRecyclerViewAdapter extends RecyclerView.Adapter<NewsletterRecyclerViewAdapter.RecyclerViewHolders>{
    private List<NewsletterItemObject> itemList;
    private Context context;
    NewsletterActivity newsletterActivity;


    public NewsletterRecyclerViewAdapter(Context context, List<NewsletterItemObject> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView newsletter_Title;
        public TextView published;
        public TextView download, end_time, registered;
        public RecyclerViewHolders(View itemView) {
            super(itemView);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
            newsletter_Title = (TextView)itemView.findViewById(R.id.newsletter);
            //published = (TextView)itemView.findViewById(R.id.published);
            download = (TextView)itemView.findViewById(R.id.download);
        }
        @Override
        public void onClick(View view) {
            Intent intent;
            int position  =   getAdapterPosition();
            System.out.println("position" +position);

            File extStore = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File("storage/sdcard1/download",itemList.get(position).getNewsletterImage());

            if(myFile.exists()){

                File file = new File("storage/sdcard1/download"+itemList.get(position).getNewsletterImage());
               intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                context.startActivity(intent);
            }

                //WebView webview = (WebView) view.findViewById(R.id.webview);

              else {
                intent = new Intent(context, NewsletterActivity.class);
                intent.putExtra("pdf_name", itemList.get(position).getNewsletterImage());
                context.startActivity(intent);
                //webview.getSettings().setJavaScriptEnabled(true);
            }
            System.out.println("Selected Newsletter" + itemList.get(position).getNewsletterImage());
            //webview.loadUrl("http://nrna.org.np/newsletter/"+itemList.get(position).getNewsletterImage());
            itemList.get(position).getNewsletterImage();

            //newsletterActivity.setURl(itemList.get(position).getNewsletterImage());
            //newsletterActivity.callWebview();
            //startDownload(itemList.get(position).getNewsletterImage());
           // http://nrna.org.np/images/newsletter/asdf.pdf
            switch (view.getId()){
                case R.id.newsletter:
                    System.out.println("Selected" + position);
                    Log.w("", "Selected" + position);
                    //itemList.get(position).getNewsletterImage();
                    break;
            }
        }
    }
    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_newsletter_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.newsletter_Title.setText( itemList.get(position).getNewsletterTitle());
        //holder.published.setText(itemList.get(position).getNewsletterCreated());
        holder.download.setText(itemList.get(position).getNewsletterImage());

    }
    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

   /* private void startDownload(String newsletter_url) {
        String url = "http://nrna.org.np/images/newsletter/"+newsletter_url;
        new DownloadFileAsync().execute(url);
    }*/

    /*@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog();
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }




*/
}
