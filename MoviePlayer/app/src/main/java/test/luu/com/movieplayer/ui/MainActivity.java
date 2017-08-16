package test.luu.com.movieplayer.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import javax.inject.Inject;

import test.luu.com.movieplayer.MovieApplication;
import test.luu.com.movieplayer.R;
import test.luu.com.movieplayer.adapter.HomeAdapter;
import test.luu.com.movieplayer.component.DaggerHomeComponent;
import test.luu.com.movieplayer.model.Movie;
import test.luu.com.movieplayer.model.Movies;
import test.luu.com.movieplayer.module.HomeModule;
import test.luu.com.movieplayer.presenter.HomePresenter;
import test.luu.com.movieplayer.service.HomeMVP;

public class MainActivity extends BaseActivity implements HomeMVP.View{

    SwipeRefreshLayout mRefreshLayout;
    BroadcastReceiver mNetworkBroadcast;
    RecyclerView mRvHome;
    HomeAdapter mAdapter;

    @Inject
    HomePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvHome = (RecyclerView)findViewById(R.id.rv_home);
        mRvHome.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRvHome.setLayoutManager(llm);
        mRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.home_refresh);
        mRefreshLayout.setColorSchemeResources(android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_blue_bright);
//        mEmptyView = (LinearLayout) view.findViewById(R.id.ll_empty_container);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                mRefreshLayout.setRefreshing(true);
                mPresenter.getNowPlaying();
            }
        });

        mNetworkBroadcast = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                mRefreshLayout.setRefreshing(true);
                mPresenter.getNowPlaying();
            }
        };
        registerReceiver(mNetworkBroadcast, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        createAdapter();
    }

    @Override
    protected void setupActivityComponent() {

        DaggerHomeComponent.builder()
                .dataComponent(MovieApplication.getDataComponent())
                .homeModule(new HomeModule(this))
                .build().inject(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mNetworkBroadcast != null){

            unregisterReceiver(mNetworkBroadcast);
        }
    }

    private void createAdapter(){

        mAdapter = new HomeAdapter(this, null);
        mAdapter.setListener(new HomeAdapter.Listener() {
            @Override
            public void onItemClick(Movie movie) {
                //
            }
        });
        mRvHome.setAdapter(mAdapter);

    }

    @Override
    public void onGetNowPlayingSuccess(Movies movies) {

        mRefreshLayout.setRefreshing(false);
        mAdapter.addItems(movies.getResults());
    }

    @Override
    public void onGetNowPlayingFailure(String message) {

        Toast.makeText(this, "Cannot get now playing because of: " + message, Toast.LENGTH_LONG);
    }
}
