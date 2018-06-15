package com.thirihtethtetko.tedtalkassignment.data.vos;


import java.util.List;

public class TedTalksVO {

    private String talkID;
    private String title;
    private SpeakerVO speaker;
    private String imageUrl;
    private int duationsInSec;
    private String description;
    private List<TagVO> tag;

    public String getTalkID() {
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
