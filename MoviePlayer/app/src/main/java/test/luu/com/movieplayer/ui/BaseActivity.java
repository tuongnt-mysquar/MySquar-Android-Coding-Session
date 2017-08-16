package test.luu.com.movieplayer.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by luu trinh on 8/16/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();

    }

    protected abstract void setupActivityComponent();

    protected void enableHomeUpButton(){

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
