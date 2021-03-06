package com.thirihtethtetko.tedtalkassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.viewholders.TalksViewHolder;
import com.thirihtethtetko.tedtalkassignment.viewholders.WatchNextViewHolder;

/**
 * Created by einandartun on 6/1/18.
 */

public class WatchNextAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_watch_next,parent,false);
        return new WatchNextViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}