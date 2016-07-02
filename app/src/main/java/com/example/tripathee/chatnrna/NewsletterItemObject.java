package com.example.tripathee.chatnrna;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tripathee on 6/19/2016.
 */
public class NewsletterItemObject {

    @SerializedName("title")
    private String title;

    @SerializedName("registered")
    private String registered;

    @SerializedName("created")
    private String created;

    @SerializedName("image")
    private String image;

    @SerializedName("id")
    private Integer id;


    public NewsletterItemObject(String title, String registered, String created, String image, Integer id){
        this.title= title;
        this.registered = registered;
        this.created = created;
        this.image = image;
        this.id = id;

    }

    public String getNewsletterTitle() {
        return title;
    }

    public String getNewsletterRegistered() {
        return registered;
    }

    public String getNewsletterCreated() {
        return created;
    }

    public String getNewsletterImage() {
        return image;
    }

    public Integer getNewsletterId() {
        return id;
    }
}