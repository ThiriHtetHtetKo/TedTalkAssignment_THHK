package com.thirihtethtetko.tedtalkassignment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.thirihtethtetko.tedtalkassignment.adapters.TalksAdapter;

public class TedTalkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tedtalk_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvwatchnext = findViewById(R.id.rv_watch_next);
        TalksAdapter talksAdapter = new TalksAdapter();
        rvwatchnext.setAdapter(talksAdapter);
        rvwatchnext.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));


    }

}
