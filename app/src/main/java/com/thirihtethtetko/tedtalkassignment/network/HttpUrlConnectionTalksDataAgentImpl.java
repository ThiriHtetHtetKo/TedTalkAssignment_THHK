package com.thirihtethtetko.tedtalkassignment.network;


import android.os.AsyncTask;
import android.util.EventLog;
import android.util.Log;

import com.google.gson.Gson;
import com.thirihtethtetko.tedtalkassignment.events.ApiErrorEvent;
import com.thirihtethtetko.tedtalkassignment.events.SuccessGetTalksEvent;
import com.thirihtethtetko.tedtalkassignment.network.responses.GetTalksResponse;
import com.thirihtethtetko.tedtalkassignment.utils.TalksConstants;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class HttpUrlConnectionTalksDataAgentImpl implements TalksDataAgent {

    private static HttpUrlConnectionTalksDataAgentImpl objInstance;

    private HttpUrlConnectionTalksDataAgentImpl() {

    }

    public static HttpUrlConnectionTalksDataAgentImpl getObjInstance() {

        if (objInstance == null) {

            objInstance = new HttpUrlConnectionTalksDataAgentImpl();
        }

        return objInstance;
    }

    @Override
    public void loadTalksList(final int page, final String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {

                URL url;
                BufferedReader reader = null;
                StringBuilder stringBuilder;

                try {

                    url = new URL(TalksConstants.API_BASE + TalksConstants.GET_TALKS); //1.
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //2.

                    // just want to do an HTTP POST here
                    connection.setRequestMethod("POST"); //3.

                    // uncomment this if you want to write output to this url
                    //connection.setDoOutput(true);

                    // give it 15 seconds to respond
                    connection.setReadTimeout(15 * 1000); //4. ms

                    connection.setDoInput(true); //5.
                    connection.setDoOutput(true);

                    //put the request parameter into NameValuePair list.
                    List<NameValuePair> params = new ArrayList<>(); //6.
                    params.add(new BasicNameValuePair(TalksConstants.PARAM_ACCESS_TOKEN, accessToken));
                    params.add(new BasicNameValuePair(TalksConstants.PARAM_PAGE,String.valueOf(page)));

                    //write the parameters from NameValuePair list into connection obj.
                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    outputStream.close();

                    connection.connect(); //7.

                    // read the output from the server
                    reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); //8.
                    stringBuilder = new StringBuilder();

                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line + "\n");
                    }

                    String responseString = stringBuilder.toString(); //9.
                    // AttractionListResponse response = CommonInstances.getGsonInstance().fromJson(responseString, AttractionListResponse.class);
                    //List<AttractionVO> attractionList = response.getAttractionList();
                    Log.d("msg", responseString);
                    return responseString;

                } catch (Exception e) {
                    Log.e("", e.getMessage());
                    //AttractionModel.getInstance().notifyErrorInLoadingAttractions(e.getMessage());
                } finally {
                    // close the reader; this can throw an exception too, so
                    // wrap it in another try/catch block.
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                    }
                }

                return null;
            }

            private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
                StringBuilder result = new StringBuilder();
                boolean first = true;

                for (NameValuePair pair : params) {
                    if (first)
                        first = false;
                    else
                        result.append("&");

                    result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
                    result.append("=");
                    result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
                }

                return result.toString();
            }

            /*@Override
            protected void onPreExecute(String responseString) {
                super.onPreExecute(responseString);
            }
        }.execute();*/

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