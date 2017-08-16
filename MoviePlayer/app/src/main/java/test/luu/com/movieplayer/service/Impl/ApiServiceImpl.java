package test.luu.com.movieplayer.service.Impl;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Query;
import test.luu.com.movieplayer.MovieApplication;
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
}
