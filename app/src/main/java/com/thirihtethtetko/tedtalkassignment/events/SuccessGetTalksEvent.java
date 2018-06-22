package com.thirihtethtetko.tedtalkassignment.events;

import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;

import java.util.List;

public class SuccessGetTalksEvent {

    private List<TedTalksVO> talksList;

    public SuccessGetTalksEvent(List<TedTalksVO> talksList) {
        this.talksList = talksList;
    }

    public List<TedTalksVO> getTalksList() {
        return talksList;
    }
}
