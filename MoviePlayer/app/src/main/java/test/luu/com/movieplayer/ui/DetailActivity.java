package test.luu.com.movieplayer.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import javax.inject.Inject;

import test.luu.com.movieplayer.MovieApplication;
import test.luu.com.movieplayer.R;
import test.luu.com.movieplayer.component.DaggerDetailComponent;
import test.luu.com.movieplayer.model.Movie;
import test.luu.com.movieplayer.model.MovieDetail;
import test.luu.com.movieplayer.module.DetailModule;
import test.luu.com.movieplayer.presenter.DetailPresenter;
import test.luu.com.movieplayer.service.DetailMVP;

public class DetailActivity extends BaseActivity implements DetailMVP.View{

    ImageView mImgView;

    @Inject
    DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        enableHomeUpButton();

        mImgView = (ImageView)findViewById(R.id.img_detail_main);
        int movieId = getIntent().getExtras().getInt(MovieDetail.KEY);

        mPresenter.getMovieDetail(movieId);
    }

    @Override
    protected void setupActivityComponent() {

        DaggerDetailComponent.builder()
                .dataComponent(MovieApplication.getDataComponent())
                .detailModule(new DetailModule(this))
                .build().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetDetailSuccess(MovieDetail detail) {

        if(detail != null){

            // Load image
            int width = (int)getResources().getDimension(R.dimen.img_detail_width_size);
            int height = (int)getResources().getDimension(R.dimen.img_detail_height_size);
            String posterPath = detail.getPosterPath();
            Glide.with(this).load(Movie.BASE_IMAGE_URL + posterPath).override(width, height).centerCrop().into(mImgView);

            // Load info
            TextView txtName = (TextView)findViewById(R.id.txt_title);
            txtName.setText(detail.getTitle());

            TextView txtDes = (TextView)findViewById(R.id.txt_description);
            txtDes.setText(detail.getOverview() == null ? "" : detail.getOverview());

            TextView txtWebsite = (TextView)findViewById(R.id.txt_website_content);
            txtWebsite.setText(detail.getHomepage() == null ? "" : detail.getHomepage());
        }
    }

    @Override
    public void onGetDetailFailure(String message) {

        // should be in string resources
        Toast.makeText(this, "Cannot get detail of this movie because of: " + message, Toast.LENGTH_LONG);
    }
}
