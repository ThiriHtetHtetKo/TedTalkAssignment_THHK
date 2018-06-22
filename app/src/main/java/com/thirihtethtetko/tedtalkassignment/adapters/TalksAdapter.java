package com.thirihtethtetko.tedtalkassignment.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;
import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;
import com.thirihtethtetko.tedtalkassignment.viewholders.TalksViewHolder;

import java.util.ArrayList;
import java.util.List;


public class TalksAdapter extends RecyclerView.Adapter<TalksViewHolder> {

    private TalksDelegate tedtalksDelegate;

    private List<TedTalksVO> mtalksList;


    public TalksAdapter(TalksDelegate talksDelegate){
        tedtalksDelegate = talksDelegate;
        mtalksList = new ArrayList<>();
    }

    @Override
    public TalksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.view_holder_talks,parent,false);
        return new TalksViewHolder(view,tedtalksDelegate);

    }

    @Override
    public void onBindViewHolder(TalksViewHolder holder, int position) {
        holder.setMtalks(mtalksList.get(position));
    }


    @Override
    public int getItemCount() {
        return mtalksList.size();
    }

    public void setTalksList(List<TedTalksVO> talksList){

        mtalksList = talksList;
        notifyDataSetChanged();
    }
}
