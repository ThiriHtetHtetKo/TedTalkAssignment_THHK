package com.thirihtethtetko.tedtalkassignment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.adapters.TalksAdapter;
import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;

public class TedTalkListActivity extends BaseActivity implements TalksDelegate {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tedtalk_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvtalks = findViewById(R.id.rv_talks);
        TalksAdapter talksAdapter = new TalksAdapter(this);
        rvtalks.setAdapter(talksAdapter);
        rvtalks.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

    }

    @Override
    public void onTapTalks() {
        Intent intent = new Intent(getApplicationContext(),TedTalkDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onTapFavorite() {

    }

    @Override
    public void onTapDownload() {

    }

    @Override
    public void onTapShare() {

    }

    @Override
    public void onTapPlay() {

    }
}
