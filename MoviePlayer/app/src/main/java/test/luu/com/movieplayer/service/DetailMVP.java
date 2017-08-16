package test.luu.com.movieplayer.service;

import test.luu.com.movieplayer.model.MovieDetail;
import test.luu.com.movieplayer.model.Movies;

/**
 * Created by luu trinh on 8/16/2017.
 */

public interface DetailMVP {

    // Home implement this, Presenter -> Home
    interface View {

        void onGetDetailSuccess(MovieDetail detail);

        void onGetDetailFailure(String message);
    }

    // Presenter implement this, Home -> Presenter
    interface Presenter {

        void getMovieDetail(int id);

    }

    // Presenter implement this, Model -> Presenter
    interface ModelAccess{

    }

    // Model implement this, Presenter -> Model
    interface Model{

    }
}
