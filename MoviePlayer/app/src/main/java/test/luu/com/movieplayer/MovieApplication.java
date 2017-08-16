package test.luu.com.movieplayer;

import android.app.Application;

import test.luu.com.movieplayer.component.DaggerDataComponent;
import test.luu.com.movieplayer.component.DataComponent;
import test.luu.com.movieplayer.module.AppModule;
import test.luu.com.movieplayer.module.DataModule;

/**
 * Created by luu trinh on 8/16/2017.
 */

public class MovieApplication extends Application {

    private static DataComponent mDataComponent;
    private static MovieApplication mInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        mDataComponent = DaggerDataComponent.builder()
                .appModule(getAppModule())
//                .dataModule(new DataModule("http://115.74.111.230/blacklist/public/"))
                .dataModule(getDataModule())
                .build();
        if(mInstance == null){
            mInstance = this;
        }
    }

    public AppModule getAppModule(){

        return new AppModule(this);
    }

    public DataModule getDataModule(){

        return new DataModule("https://api.themoviedb.org/3/");
    }

    public static MovieApplication getInstance(){
        return mInstance;
    }

    public static DataComponent getDataComponent(){
        return mDataComponent;
    }
}
