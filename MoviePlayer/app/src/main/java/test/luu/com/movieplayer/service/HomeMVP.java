package test.luu.com.movieplayer.service;

import test.luu.com.movieplayer.model.Movies;

/**
 * Created by luu trinh on 8/16/2017.
 */

public interface HomeMVP {

    // Home implement this, Presenter -> Home
    interface View {

        void onGetNowPlayingSuccess(Movies movies);

        void onGetNowPlayingFailure(String message);
    }

    // Presenter implement this, Home -> Presenter
    interface Presenter {

        void getNowPlaying();

    }

    // Presenter implement this, Model -> Presenter
    interface ModelAccess{

    }

    // Model implement this, Presenter -> Model
    interface Model{

    }
}
