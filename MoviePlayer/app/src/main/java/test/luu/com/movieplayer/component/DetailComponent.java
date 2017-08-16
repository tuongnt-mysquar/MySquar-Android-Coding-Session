package test.luu.com.movieplayer.component;

import dagger.Component;
import test.luu.com.movieplayer.module.DetailModule;
import test.luu.com.movieplayer.scope.DetailScope;
import test.luu.com.movieplayer.ui.DetailActivity;

/**
 * Created by luu trinh on 8/16/2017.
 */

@DetailScope
@Component(dependencies = {DataComponent.class}, modules = {DetailModule.class})
public interface DetailComponent {

    void inject(DetailActivity activity);
}
