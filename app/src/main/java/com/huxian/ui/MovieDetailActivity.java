package com.huxian.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.ComedyApplication;
import com.huxian.R;
import com.huxian.injector.component.DaggerMovieDetailComponent;
import com.huxian.injector.module.ActivityModule;
import com.huxian.injector.module.MovieDetailModule;
import com.huxian.model.Movie;
import com.huxian.presenter.MovieDetailPresenter;
import com.huxian.ui.view.IMovieDetailView;

import javax.inject.Inject;

/**
 * @author huxian99
 */
public class MovieDetailActivity extends BaseActivity implements IMovieDetailView {

    public static final String INTENT_MOVIE_ID = "movie_id";

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    private String movieId;

    private TextView tvMovieTitle;
    private ImageView ivMoviePoster;
    private ImageView ivBlurBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initData();
        initView();
        initDependencyInjector();
        initPresenter();
    }

    private void initData() {
        movieId = getIntent().getStringExtra(INTENT_MOVIE_ID);
    }

    private void initView() {
        tvMovieTitle = (TextView) findViewById(R.id.tv_movie_detail_title);
        ivMoviePoster = (ImageView) findViewById(R.id.iv_movie_detail_poster);
        ivBlurBackground = (ImageView) findViewById(R.id.iv_movie_basic_info_background);
    }

    private void initDependencyInjector() {
        ComedyApplication comedyApplication = (ComedyApplication) getApplication();
        DaggerMovieDetailComponent.builder()
                .movieDetailModule(new MovieDetailModule(movieId))
                .comedyComponent(comedyApplication.component())
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    private void initPresenter() {
        movieDetailPresenter.attachView(this);
        movieDetailPresenter.onCreate();
    }

    @Override
    public void showMovie(Movie movie) {
        tvMovieTitle.setText(movie.getTitle());
    }
}