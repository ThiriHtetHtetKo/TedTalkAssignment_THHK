package com.thirihtethtetko.tedtalkassignment.data.models;


import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;
import com.thirihtethtetko.tedtalkassignment.events.SuccessGetTalksEvent;
import com.thirihtethtetko.tedtalkassignment.network.TalksDataAgent;
import com.thirihtethtetko.tedtalkassignment.network.HttpUrlConnectionTalksDataAgentImpl;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class TedTalksModel {

    public static final  String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private static TedTalksModel objInstance;

    private TalksDataAgent talksDataAgent;

    private Map<Integer,TedTalksVO> mTalksMap;

    private TedTalksModel() {

        talksDataAgent = HttpUrlConnectionTalksDataAgentImpl.getObjInstance();

        mTalksMap = new HashMap<>();

        EventBus.getDefault().register(this);


    }

    public static TedTalksModel getObjInstance(){

        if(objInstance == null){

            objInstance = new TedTalksModel();
        }

        return objInstance;
    }

    public TedTalksVO getTalksById(int talkId){
        return mTalksMap.get(talkId);
    }
    public void loadTalksList(){

        talksDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetTalks(SuccessGetTalksEvent event){

        for(TedTalksVO talks:event.getTalksList()){
            mTalksMap.put(talks.getTalkID(),talks);
        }
    }
}
