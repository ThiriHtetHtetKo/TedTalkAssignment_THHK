package com.thirihtethtetko.tedtalkassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;
import com.thirihtethtetko.tedtalkassignment.viewholders.TalksViewHolder;


public class TalksAdapter extends RecyclerView.Adapter {

    private TalksDelegate tedtalksDelegate;

    public TalksAdapter(TalksDelegate talksDelegate){
        tedtalksDelegate = talksDelegate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talks,parent,false);
        return new TalksViewHolder(view,tedtalksDelegate);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
