package com.thirihtethtetko.tedtalkassignment.data.vos;

import java.util.List;

/**
 * Created by einandartun on 6/14/18.
 */

public class TedPlaylistsVO {

    private int playlistId;
    private String title;
    private String imageUrl;
    private int totalTalks;
    private String description;
    private List<TedTalksVO> talksInPlaylist;

    public int getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalTalks() {
        return totalTalks;
    }

    public String getDescription() {
        return description;
    }

    public List<TedTalksVO> getTalksInPlaylist() {
        return talksInPlaylist;
    }
}
