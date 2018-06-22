package com.thirihtethtetko.tedtalkassignment.network;

import com.thirihtethtetko.tedtalkassignment.network.responses.GetTalksResponse;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TalksApi {

    @FormUrlEncoded
    @POST(TalksConstants.GET_TALKS)
    Call<GetTalksResponse> loadTalkslist(
            @Field(TalksConstants.PARAM_ACCESS_TOKEN) String accessToken,
            @Field(TalksConstants.PARAM_PAGE) int page);

}
