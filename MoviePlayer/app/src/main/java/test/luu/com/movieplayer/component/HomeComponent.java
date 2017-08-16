package test.luu.com.movieplayer.component;

import dagger.Component;
import test.luu.com.movieplayer.ui.MainActivity;
import test.luu.com.movieplayer.module.HomeModule;
import test.luu.com.movieplayer.scope.HomeScope;

/**
 * Created by luu trinh on 8/16/2017.
 */

@HomeScope
@Component(dependencies = {DataComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {

    void inject(MainActivity activity);
}
