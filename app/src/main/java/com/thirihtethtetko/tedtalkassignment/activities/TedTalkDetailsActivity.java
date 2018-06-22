package com.thirihtethtetko.tedtalkassignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.adapters.TalksAdapter;
import com.thirihtethtetko.tedtalkassignment.adapters.WatchNextAdapter;
import com.thirihtethtetko.tedtalkassignment.data.models.TedTalksModel;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TedTalkDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_speaker)
    TextView tvSpeaker;

    @BindView(R.id.iv_news_backdrop)
    ImageView ivNewsBackdrop;

    @BindView(R.id.tv_tedtalk_details)
    TextView tvTalkDetail;

    @BindView(R.id.tv_speaker_and_title)
    TextView tvSpeakerAndTitle;

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakerName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tedtalk_details);

        ButterKnife.bind(this, this);
        int defaultValue = 0;
        int talkID = getIntent().getIntExtra("talkId",defaultValue);
        Log.d("TalksDetailsActivity", "talkID : " + talkID);

        TedTalksVO talks = TedTalksModel.getObjInstance().getTalksById(talkID);

        Log.d("talk " ,"talkID : " + talks.getTalkID());

        bindData(talks);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvWatchNext = findViewById(R.id.rv_watch_next);
        WatchNextAdapter watchNextAdapter = new WatchNextAdapter();
        rvWatchNext.setAdapter(watchNextAdapter);
        rvWatchNext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

    }

    private void bindData(TedTalksVO talks){

        tvSpeaker.setText(talks.getSpeaker().getName());

        Glide.with(ivNewsBackdrop.getContext())
                .load(talks.getImageUrl())
                .into(ivNewsBackdrop);

        tvSpeakerAndTitle.setText(talks.getTitle());

        tvTime.setText(secondsToString(talks.getDuationsInSec()));

        tvTalkDetail.setText(talks.getDescription());

        tvSpeakerName.setText(talks.getSpeaker().getName());
    }

    public String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }
}
