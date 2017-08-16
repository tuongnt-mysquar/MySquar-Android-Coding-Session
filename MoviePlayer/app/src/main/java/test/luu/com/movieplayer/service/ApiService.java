package test.luu.com.movieplayer.service;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import test.luu.com.movieplayer.model.MovieDetail;
import test.luu.com.movieplayer.model.Movies;

/**
 * Created by luu trinh on 8/16/2017.
 */

public interface ApiService {

    // With Rx java
    @GET("/3/movie/now_playing")
    @Headers("Cache-Control: no-cache")
    Observable<Movies> getNowPlaying2(@Query("api_key") String key);

    //@FormUrlEncoded
    @GET("/3/movie/now_playing")
    @Headers("Cache-Control: no-cache")
    Call<Movies> getNowPlaying(@Query("api_key") String key);

    @GET("/3/movie/{movie_id}")
    @Headers("Cache-Control: no-cache")
    Call<MovieDetail> getMovieDetail( @Path("movie_id") int id, @Query("api_key") String key);
}
