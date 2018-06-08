package com.thirihtethtetko.tedtalkassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;


public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate tedTalksDelegate;

    public TalksViewHolder(View itemView, TalksDelegate talksDelegate)
    {
        super(itemView);

        tedTalksDelegate = talksDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tedTalksDelegate.onTapTalks();
            }
        });
    }
}
