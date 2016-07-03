package com.example.tripathee.chatnrna;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Waters on 7/3/2016.
 */
public class LoginData {

    @SerializedName("app_user_id")
    private String app_user_id;

    public String getApp_user_id() {
        return app_user_id;
    }

    public void setApp_user_id(String app_user_id) {
        this.app_user_id = app_user_id;
    }
}
