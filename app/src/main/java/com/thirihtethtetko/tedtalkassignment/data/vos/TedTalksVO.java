package com.thirihtethtetko.tedtalkassignment.data.vos;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TedTalksVO {

    @SerializedName("talk_id")
    private int talkID;

    @SerializedName("title")
    private String title;

    @SerializedName("speaker")
    private SpeakerVO speaker;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("durationInSec")
    private int duationsInSec;

    @SerializedName("description")
    private String description;

    @SerializedName("tag")
    private List<TagVO> tag;

    public int getTalkID() {
        return talkID;
    }

    public String getTitle() {
        return title;
    }

    public SpeakerVO getSpeaker() {
        return speaker;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDuationsInSec() {
        return duationsInSec;
    }

    public String getDescription() {
        return description;
    }

    public List<TagVO> getTag() {
        return tag;
    }
}
