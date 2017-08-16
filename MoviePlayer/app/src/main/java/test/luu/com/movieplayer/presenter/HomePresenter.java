package test.luu.com.movieplayer.presenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import test.luu.com.movieplayer.model.Movies;
import test.luu.com.movieplayer.service.ApiService;
import test.luu.com.movieplayer.service.HomeMVP;

/**
 * Created by luu trinh on 8/16/2017.
 */

public class HomePresenter implements HomeMVP.Presenter{

    ApiService mApi;
    HomeMVP.View mView;

    @Inject
    public HomePresenter(ApiService api, HomeMVP.View view){

        this.mApi = api;
        this.mView = view;
    }

    @Override
    public void getNowPlaying() {

        mApi.getNowPlaying("").enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {

                if(response.isSuccessful()){

                    mView.onGetNowPlayingSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

                mView.onGetNowPlayingFailure(t.getMessage());
            }
        });
    }

    public void getNowPlaying2(){

        mApi.getNowPlaying2(null).subscribe(new Observer<Movies>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.onGetNowPlayingFailure(e.getMessage());
            }

            @Override
            public void onNext(Movies movies) {

                mView.onGetNowPlayingSuccess(movies);
            }
        });
    }
}
