package com.thirihtethtetko.tedtalkassignment.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.thirihtethtetko.tedtalkassignment.R;
import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;
import com.thirihtethtetko.tedtalkassignment.delegates.TalksDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TalksViewHolder extends RecyclerView.ViewHolder {

    private TalksDelegate tedTalksDelegate;

    private TedTalksVO mtalks;

    @BindView(R.id.tv_speak_title)
    TextView tvSpeakTitle;

    @BindView(R.id.tv_speaker_name)
    TextView tvSpeakername;

    @BindView(R.id.tv_duation)
    TextView tvDuation;

    @BindView(R.id.iv_speaker_photo)
    ImageView ivSpeakerPhoto;

    public TalksViewHolder(View itemView, TalksDelegate talksDelegate)
    {
        super(itemView);
        ButterKnife.bind(this, itemView);
        tedTalksDelegate = talksDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tedTalksDelegate.onTapTalks(mtalks);
            }
        });
    }

    public void setMtalks(TedTalksVO talks){
        mtalks = talks;
        tvSpeakTitle.setText(talks.getTitle());

        tvSpeakername.setText(talks.getSpeaker().getName());

        tvDuation.setText(secondsToString(talks.getDuationsInSec()));

        Glide.with(ivSpeakerPhoto.getContext())
                .load(talks.getImageUrl())
                .into(ivSpeakerPhoto);
    }

    public String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }
}
