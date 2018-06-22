package com.thirihtethtetko.tedtalkassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.util.Log;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.adapters.TalksAdapter;
import com.thirihtethtetko.tedtalkassignment.data.models.TedTalksModel;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;
import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;
import com.thirihtethtetko.tedtalkassignment.events.SuccessGetTalksEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TedTalkListActivity extends BaseActivity implements TalksDelegate {

    private TalksAdapter mTalksAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tedtalk_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvtalks = findViewById(R.id.rv_talks);
        mTalksAdapter = new TalksAdapter(this);
        rvtalks.setAdapter(mTalksAdapter);
        rvtalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

        TedTalksModel.getObjInstance().loadTalksList();
    }

    @Override
    protected void onStart(){
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop(){
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onTapTalks(TedTalksVO talks) {
        Intent intent = new Intent(getApplicationContext(),TedTalkDetailsActivity.class);
        intent.putExtra("talkId",talks.getTalkID());
        startActivity(intent);
    }

    @Override
    public void onTapFavorite(TedTalksVO talks) {

    }

    @Override
    public void onTapDownload(TedTalksVO talks) {

    }

    @Override
    public void onTapShare(TedTalksVO talks) {

    }

    @Override
    public void onTapPlay(TedTalksVO talks) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetTalks(SuccessGetTalksEvent event){
        Log.d("onSuccessGetTalks","onSuccessGetTalks : " + event.getTalksList().size());
        mTalksAdapter.setTalksList(event.getTalksList());
    }
}
