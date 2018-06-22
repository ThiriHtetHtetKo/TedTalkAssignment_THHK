package com.thirihtethtetko.tedtalkassignment.network;


import com.thirihtethtetko.tedtalkassignment.events.ApiErrorEvent;
import com.thirihtethtetko.tedtalkassignment.events.SuccessGetTalksEvent;
import com.thirihtethtetko.tedtalkassignment.network.responses.GetTalksResponse;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;

import org.greenrobot.eventbus.EventBus;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements TalksDataAgent {

    private static RetrofitDataAgentImpl sObjInstance;

    private TalksApi mTheApi;


    private RetrofitDataAgentImpl(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
                .writeTimeout(15,java.util.concurrent.TimeUnit.SECONDS )
                .readTimeout(15,java.util.concurrent.TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TalksConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(TalksApi.class);
    }

    public static RetrofitDataAgentImpl getsObjInstance(){
        if(sObjInstance == null ){
            sObjInstance= new RetrofitDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadTalksList(int page, String accessToken) {

        Call<GetTalksResponse> loadTalksCall = mTheApi.loadTalkslist(accessToken,page);
        loadTalksCall.enqueue(new Callback<GetTalksResponse>() {
            @Override
            public void onResponse(Call<GetTalksResponse> call, Response<GetTalksResponse> response) {

                GetTalksResponse talksResponse =  response.body();

                if(talksResponse != null && talksResponse.isResponseOK()){

                    SuccessGetTalksEvent event = new SuccessGetTalksEvent(talksResponse.getTedTalks());
                    EventBus.getDefault().post(event);
                } else {
                    if(talksResponse == null){
                        ApiErrorEvent event = new ApiErrorEvent("Empty response in network call");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(talksResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetTalksResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
