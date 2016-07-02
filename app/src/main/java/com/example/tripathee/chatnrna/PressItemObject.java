package com.example.tripathee.chatnrna;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Tripathee on 6/19/2016.
 */
public class PressItemObject {

    @SerializedName("title")
    private String press_title;

    @SerializedName("content")
    private String press_registered;

    @SerializedName("press_year")
    private String press_created;

    @SerializedName("pdf_upload")
    private String press_image;

    @SerializedName("id")
    private Integer press_id;

    public PressItemObject(String press_title,String press_registered,String press_created,String press_image,Integer press_id){
        this.press_title = press_title;
        this.press_registered = press_registered;
        this.press_created = press_created;
        this.press_image = press_image;
        this.press_id = press_id;
    }

    public String getTitle() {
        return press_title;
    }

    public String getRegistered() {
        return press_registered;
    }

    public String getCreated() {
        return press_created;
    }

    public String getImage() {
        return press_image;
    }

    public Integer getId() {
        return press_id;
    }
}
