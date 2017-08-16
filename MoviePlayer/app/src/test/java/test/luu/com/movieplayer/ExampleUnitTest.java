package test.luu.com.movieplayer;

import org.junit.Rule;
import org.junit.Test;

import it.cosenonjaviste.daggermock.DaggerMockRule;
import test.luu.com.movieplayer.component.DataComponent;
import test.luu.com.movieplayer.module.DataModule;
import test.luu.com.movieplayer.service.ApiService;
import test.luu.com.movieplayer.service.Impl.ApiServiceImpl;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

//    @Rule
//    public DaggerMockRule<DataComponent> mockitoRule =
//            new DaggerMockRule<>(DataComponent.class, new DataModule("https://api.themoviedb.org/3/"))
//                    .set(component -> mApi = component.apiService());

    ApiService mApi;

    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
}