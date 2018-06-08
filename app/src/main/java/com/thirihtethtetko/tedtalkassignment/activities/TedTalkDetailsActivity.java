package com.thirihtethtetko.tedtalkassignment.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.adapters.TalksAdapter;
import com.thirihtethtetko.tedtalkassignment.adapters.WatchNextAdapter;

public class TedTalkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tedtalk_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvwatchnext = findViewById(R.id.rv_watch_next);
        WatchNextAdapter watchNextAdapter = new WatchNextAdapter();
        rvwatchnext.setAdapter(watchNextAdapter);
        rvwatchnext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));


    }

}
