package test.luu.com.movieplayer.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import test.luu.com.movieplayer.service.ApiService;
import test.luu.com.movieplayer.service.Impl.ApiServiceImpl;

/**
 * Created by luu trinh on 8/16/2017.
 */

@Module
public class DataModule {
    String mBaseUrl;

    public DataModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Cache provideHttpCache(Application application) {
        File httpCacheDirectory = new File(application.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(httpCacheDirectory, cacheSize);
//        int cacheSize = 10 * 1024 * 1024;
//        Cache cache = new Cache(application.getCacheDir(), cacheSize);
//        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.setLenient();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        //gsonBuilder.excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
        return gsonBuilder.create();
    }

    // https://stackoverflow.com/questions/23429046/can-retrofit-with-okhttp-use-cache-data-when-offline
    private static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Response originalResponse = chain.proceed(chain.request());
            int maxAge = 1; // read from cache for 1 second
            maxAge = 60 * 60 * 24 * 7;
            return originalResponse.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();

        }
    };
    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        //https://stackoverflow.com/questions/39918814/use-jsonreader-setlenienttrue-to-accept-malformed-json-at-line-1-column-1-path
        //client.interceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR);
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        client.interceptors().add(interceptor);
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

//
//    @Singleton
//    @Provides
//    LoggerService provideLoggerService(){
//
//        return new LoggerImpl();
//    }
//
    @Provides
    @Singleton
    ApiService provideApiService(){

        return new ApiServiceImpl();
    }
//
//    @Provides
//    @Singleton
//    LocalStorageService provideStorageService(){
//
//        return new LocalStorageImpl();
//    }
}

