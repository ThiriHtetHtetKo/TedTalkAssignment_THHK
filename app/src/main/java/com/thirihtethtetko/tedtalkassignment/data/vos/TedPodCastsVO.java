package com.thirihtethtetko.tedtalkassignment.data.vos;

import java.util.List;

/**
 * Created by einandartun on 6/15/18.
 */

public class TedPodCastsVO {

    private String podCastId;
    private String title;
    private String imageUrl;
    private String description;
    private List<SegmentsVO> segments;

    public String getPodCastId() {
        return podCastId;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public List<SegmentsVO> getSegments() {
        return segments;
    }
}
