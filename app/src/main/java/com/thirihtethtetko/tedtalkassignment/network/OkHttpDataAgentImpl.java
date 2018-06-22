package com.thirihtethtetko.tedtalkassignment.network;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;
import com.thirihtethtetko.tedtalkassignment.events.ApiErrorEvent;
import com.thirihtethtetko.tedtalkassignment.events.SuccessGetTalksEvent;
import com.thirihtethtetko.tedtalkassignment.network.TalksDataAgent;
import com.thirihtethtetko.tedtalkassignment.network.responses.GetTalksResponse;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OkHttpDataAgentImpl implements TalksDataAgent {

    private static OkHttpDataAgentImpl sObjectInstance;

    private OkHttpClient mHttpClient;

    public OkHttpDataAgentImpl() {

        mHttpClient = new OkHttpClient.Builder().connectTimeout(15, TimeUnit.SECONDS).writeTimeout(15,TimeUnit.SECONDS).readTimeout(15,TimeUnit.SECONDS).build();

    }

    public static OkHttpDataAgentImpl getsObjectInstance(){

        if(sObjectInstance == null){
            sObjectInstance = new OkHttpDataAgentImpl();
        }
        return sObjectInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... voids) {
                RequestBody formBody = new FormBody.Builder() //2.
                        .add(TalksConstants.PARAM_ACCESS_TOKEN, accessToken)
                        .add(TalksConstants.PARAM_PAGE,String.valueOf(page))
                        .build();

                Request request = new Request.Builder() //3
                        .url(TalksConstants.API_BASE + TalksConstants.GET_TALKS)
                        .post(formBody)
                        .build();

                try {
                    Response response = mHttpClient.newCall(request).execute(); //4.
                    if (response.isSuccessful()) {

                        String responseString = response.body().string();

                        return responseString;
                    }

                } catch (IOException e) {
                    Log.e("LoadNewsList", e.getMessage());
                    //AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
                }

                return null;
            }

            @Override
            protected void onPostExecute(String responseString) {
                super.onPostExecute(responseString);

                Gson gson = new Gson();
                GetTalksResponse talksResponse = gson.fromJson(responseString,GetTalksResponse.class);
                Log.d("onPostExecute","Talks List Size : "+talksResponse.getTedTalks().size());

                if(talksResponse.isResponseOK()){
                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    ApiErrorEvent event = new ApiErrorEvent(talksResponse.getMessage());
                    EventBus.getDefault().post(event);


                }
            }
        }.execute();
    }
}
