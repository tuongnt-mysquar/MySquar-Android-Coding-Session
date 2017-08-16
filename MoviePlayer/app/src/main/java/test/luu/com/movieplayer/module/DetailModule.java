package test.luu.com.movieplayer.module;

import dagger.Module;
import dagger.Provides;
import test.luu.com.movieplayer.scope.DetailScope;
import test.luu.com.movieplayer.service.DetailMVP;

/**
 * Created by luu trinh on 8/16/2017.
 */

@Module
public class DetailModule {

    final DetailMVP.View mView;

    public DetailModule(DetailMVP.View mView) {
        this.mView = mView;
    }

    @DetailScope
    @Provides
    DetailMVP.View provideDetailView(){

        return mView;
    }
}
