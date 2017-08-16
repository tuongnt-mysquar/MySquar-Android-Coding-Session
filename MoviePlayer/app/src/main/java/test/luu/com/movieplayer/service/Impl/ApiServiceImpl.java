package test.luu.com.movieplayer.service.Impl;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import test.luu.com.movieplayer.MovieApplication;
import test.luu.com.movieplayer.model.MovieDetail;
import test.luu.com.movieplayer.model.Movies;
import test.luu.com.movieplayer.service.ApiService;

/**
 * Created by luu trinh on 8/16/2017.
 */

public class ApiServiceImpl implements ApiService {

    @Inject
    Retrofit mRetrofit;
    @Inject
    SharedPreferences mSharePref;
    @Inject
    OkHttpClient mOkHttp;
    @Inject
    Cache mCache;
    @Inject
    Gson mGson;
//    @Inject
//    LoggerService mLogger;


    public ApiServiceImpl(){
        MovieApplication.getDataComponent().inject(this);
    }

    private String getApiKey(){

        return "a9585ab8c1ea00201067f94845b77700";
    }

    @Override
    public Call<Movies> getNowPlaying(@Query("api_key") String key) {

        return mRetrofit.create(ApiService.class)
                .getNowPlaying(getApiKey());
    }

    @Override
    public Call<MovieDetail> getMovieDetail( @Path("movie_id") int id, @Query("api_key") String key) {

        return mRetrofit.create(ApiService.class)
                .getMovieDetail(id, getApiKey());
    }

    @Override
    public Observable<Movies> getNowPlaying2(@Query("api_key") String key) {

        return mRetrofit.create(ApiService.class)
                .getNowPlaying2(getApiKey())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io());

    }
}
