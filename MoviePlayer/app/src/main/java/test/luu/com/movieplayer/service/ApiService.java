package test.luu.com.movieplayer.service;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.luu.com.movieplayer.model.Movies;

/**
 * Created by luu trinh on 8/16/2017.
 */

public interface ApiService {

    //@FormUrlEncoded
    @GET("/3/movie/now_playing")
    Call<Movies> getNowPlaying(@Query("api_key") String key);
}
