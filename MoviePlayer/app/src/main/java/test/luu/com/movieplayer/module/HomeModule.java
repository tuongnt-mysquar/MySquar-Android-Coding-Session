package test.luu.com.movieplayer.module;

import dagger.Module;
import dagger.Provides;
import test.luu.com.movieplayer.scope.HomeScope;
import test.luu.com.movieplayer.service.HomeMVP;

/**
 * Created by luu trinh on 8/16/2017.
 */

@Module
public class HomeModule {

    final HomeMVP.View mView;

    public HomeModule(HomeMVP.View mView) {
        this.mView = mView;
    }

    @HomeScope
    @Provides
    HomeMVP.View provideHomeView(){

        return mView;
    }
}
