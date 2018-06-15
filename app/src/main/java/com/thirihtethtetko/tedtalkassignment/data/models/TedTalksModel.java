package com.thirihtethtetko.tedtalkassignment.data.models;


import com.thirihtethtetko.tedtalkassignment.network.TalksDataAgent;
import com.thirihtethtetko.tedtalkassignment.network.HttpUrlConnectionTalksDataAgentImpl;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;

public class TedTalksModel {

    public static final  String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private static TedTalksModel objInstance;

    private TalksDataAgent talksDataAgent;

    private TedTalksModel() {

        talksDataAgent = HttpUrlConnectionTalksDataAgentImpl.getObjInstance();
    }

    public static TedTalksModel getObjInstance(){

        if(objInstance == null){

            objInstance = new TedTalksModel();
        }

        return objInstance;
    }

    public void loadTalksList(){

        talksDataAgent.loadTalksList(1, DUMMY_ACCESS_TOKEN);

    }
}
