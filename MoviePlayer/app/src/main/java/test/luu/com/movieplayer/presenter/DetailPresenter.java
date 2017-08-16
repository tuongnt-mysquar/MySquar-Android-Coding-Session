package test.luu.com.movieplayer.presenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.luu.com.movieplayer.model.MovieDetail;
import test.luu.com.movieplayer.service.ApiService;
import test.luu.com.movieplayer.service.DetailMVP;

/**
 * Created by luu trinh on 8/16/2017.
 */

public class DetailPresenter implements DetailMVP.Presenter{

    ApiService mApi;
    DetailMVP.View mView;

    @Inject
    public DetailPresenter(ApiService api, DetailMVP.View view){

        this.mApi = api;
        this.mView = view;
    }
    @Override
    public void getMovieDetail(int id) {

        mApi.getMovieDetail(id, null).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {

                if(response.isSuccessful()){

                    mView.onGetDetailSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {

                mView.onGetDetailFailure(t.getMessage());
            }
        });
    }
}
