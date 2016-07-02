package com.example.tripathee.chatnrna;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tripathee on 6/19/2016.
 */
public class ItemObject {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @SerializedName("registered")
    private String registered;

    @SerializedName("event_stdate")
    private String event_stdate;

    @SerializedName("event_endate")
    private String event_endate;

    public ItemObject(String title,String content,String registered,String event_stdate,String event_endate ){
        this.title = title;
        this.content = content;
        this.registered = registered;
        this.event_stdate= event_stdate;
        this.event_endate = event_endate;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getRegistered() {
        return registered;
    }

    public String getEvent_stdate() {
        return event_stdate;
    }

    public String getEvent_endate() {
        return event_endate;
    }
}
