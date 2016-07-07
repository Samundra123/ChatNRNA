package com.example.tripathee.chatnrna;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Waters on 7/7/2016.
 */
public class NoticeObject{

        @SerializedName("notice_id")
        private Integer notice_id;

    @SerializedName("notice_description")
    private String notice_description;

        @SerializedName("notice_title")
        private String notice_title;

        @SerializedName("ndate")
        private String ndate;

    public NoticeObject(Integer notice_id,String notice_description,String notice_title,String ndate){
        this.notice_id = notice_id;
        this.notice_description = notice_description;
        this.notice_title = notice_title;
        this.ndate = ndate;
    }

    public Integer getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public String getNdate() {
        return ndate;
    }

    public void setNdate(String ndate) {
        this.ndate = ndate;
    }

    public String getNotice_title() {
        return notice_title;
    }

    public void setNotice_title(String notice_title) {
        this.notice_title = notice_title;
    }

    public String getNotice_description() {
        return notice_description;
    }

    public void setNotice_description(String notice_description) {
        this.notice_description = notice_description;
    }
}
