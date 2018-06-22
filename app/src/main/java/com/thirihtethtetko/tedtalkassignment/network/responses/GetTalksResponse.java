package com.thirihtethtetko.tedtalkassignment.network.responses;

import com.google.gson.annotations.SerializedName;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;

import java.util.List;

public class GetTalksResponse {

    private int code;

    private String message;

    private String apiVersion;

    private String page;

    @SerializedName("ted_talks")
    private List<TedTalksVO> tedTalks;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<TedTalksVO> getTedTalks() {
        return tedTalks;
    }

    public boolean isResponseOK(){
        return (code == 200 && tedTalks!= null);

    }
}
