package com.thirihtethtetko.tedtalkassignment.delegates;

import com.thirihtethtetko.tedtalkassignment.data.vos.TedTalksVO;

/**
 * Created by einandartun on 6/7/18.
 */

public interface TalksDelegate {

    void onTapTalks(TedTalksVO talks);

    void onTapFavorite(TedTalksVO talks);

    void onTapDownload(TedTalksVO talks);

    void onTapShare(TedTalksVO talks);

    void onTapPlay(TedTalksVO talks);

}
