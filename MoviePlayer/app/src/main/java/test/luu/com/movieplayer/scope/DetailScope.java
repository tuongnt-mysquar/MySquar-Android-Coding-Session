package test.luu.com.movieplayer.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by luu trinh on 8/16/2017.
 */

@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface DetailScope {
}
