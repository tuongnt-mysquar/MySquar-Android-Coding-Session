package test.luu.com.movieplayer.component;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import test.luu.com.movieplayer.module.AppModule;
import test.luu.com.movieplayer.module.DataModule;
import test.luu.com.movieplayer.service.ApiService;
import test.luu.com.movieplayer.service.Impl.ApiServiceImpl;

/**
 * Created by luu trinh on 8/16/2017.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface DataComponent {
    // downstream components need these exposed with the return type
    // method name does not really matter

    Application application();
    //Retrofit retrofit();
    //Realm realm();
    SharedPreferences sharePreference();
    //Cache okHttpCache();
    Gson gson();
    //OkHttpClient okHttpClient();
//    LoggerService logger();
    ApiService apiService();
//    LocalStorageService localStorageService();

    //void inject(LocalStorageImpl storage);
    void inject(ApiServiceImpl api);
    //void inject(MyFirebaseInstanceIDService service);

}
