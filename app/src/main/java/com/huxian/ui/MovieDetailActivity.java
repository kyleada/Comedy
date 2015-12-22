package com.huxian.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.ComedyApplication;
import com.huxian.R;
import com.huxian.adapter.MovieActorAdapter;
import com.huxian.injector.component.DaggerMovieDetailComponent;
import com.huxian.injector.module.ActivityModule;
import com.huxian.injector.module.MovieDetailModule;
import com.huxian.model.Actor;
import com.huxian.model.Movie;
import com.huxian.presenter.MovieDetailPresenter;
import com.huxian.ui.view.IMovieDetailView;
import com.huxian.util.BlurUtil;
import com.huxian.util.StringUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @author huxian99
 */
public class MovieDetailActivity extends BaseActivity implements IMovieDetailView {

    public static final String INTENT_MOVIE = "movie";

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    private Movie movie;
    private String movieId;
    private boolean isExpansion = false;
    private int summaryLineCount = 0;
    private MovieActorAdapter actorAdapter;
    private List<Actor> actorList;

    private TextView tvMovieTitle;
    private TextView tvOriginalTitle;
    private TextView tvGenres;
    private ImageView ivMoviePoster;
    private ImageView ivBlurBackground;
    private TextView tvSummary;
    private TextView tvExpansion;
    private RecyclerView rvActor;

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
        movie = (Movie) getIntent().getSerializableExtra(INTENT_MOVIE);
        movieId = movie.getId();
    }

    private void initView() {
        tvMovieTitle = (TextView) findViewById(R.id.tv_movie_detail_title);
        tvOriginalTitle = (TextView) findViewById(R.id.tv_movie_detail_original_title);
        tvGenres = (TextView) findViewById(R.id.tv_movie_detail_genres);
        ivMoviePoster = (ImageView) findViewById(R.id.iv_movie_detail_poster);
        ivBlurBackground = (ImageView) findViewById(R.id.iv_movie_basic_info_background);
        tvSummary = (TextView) findViewById(R.id.tv_movie_detail_summary);
        tvExpansion = (TextView) findViewById(R.id.tv_movie_detail_expansion);
        rvActor = (RecyclerView) findViewById(R.id.rv_movie_detail_actor);

        if (movie.getImages() != null) {
            blurBackground(movie.getImages().getLarge());
        }
        tvMovieTitle.setText(movie.getTitle());
        tvOriginalTitle.setText(movie.getOriginal_title());
        tvGenres.setText(StringUtil.listInsertComma(movie.getGenres()));
        tvSummary.setOnClickListener(summaryExpansionListener);
        tvExpansion.setOnClickListener(summaryExpansionListener);
        LinearLayoutManager llmActor = new LinearLayoutManager(this);
        llmActor.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvActor.setLayoutManager(llmActor);
        actorList = new ArrayList<>();
        actorAdapter = new MovieActorAdapter(this, actorList);
        rvActor.setAdapter(actorAdapter);
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

    private View.OnClickListener summaryExpansionListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            tvSummary.setText(movie.getSummary());
            if (isExpansion) {
                animExpansion(summaryLineCount, 2);
            } else {
                animExpansion(2, summaryLineCount);
            }
            isExpansion = !isExpansion;
        }
    };

    private void animExpansion(int fromLineCount, final int toLineCount) {
        int lineHeight = tvSummary.getLineHeight();
        ValueAnimator anim = ValueAnimator.ofInt(fromLineCount * lineHeight, toLineCount * lineHeight);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams params = tvSummary.getLayoutParams();
                params.height = value;
                tvSummary.setLayoutParams(params);
            }
        });
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (isExpansion) {
                    tvSummary.setMaxLines(toLineCount);
                }
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                if (!isExpansion) {
                    tvSummary.setMaxLines(toLineCount);
                }
            }
        });
        anim.setDuration(200);
        anim.start();
    }

    private void initPresenter() {
        movieDetailPresenter.attachView(this);
        movieDetailPresenter.onCreate();
    }

    @Override
    public void showMovie(Movie movie) {
        this.movie = movie;
        tvMovieTitle.setText(movie.getTitle());
        Picasso.with(MovieDetailActivity.this).load(movie.getImages().getMedium()).into(ivMoviePoster);
        if (!TextUtils.isEmpty(movie.getSummary())) {
            tvSummary.setText(movie.getSummary());
            summaryLineCount = tvSummary.getLineCount();
            tvSummary.setMaxLines(2);
        } else {
            tvSummary.setVisibility(View.GONE);
            tvExpansion.setVisibility(View.GONE);
        }
        if (movie.getDirectors() != null && movie.getDirectors().size() > 0) {
            actorList.clear();
            actorList.addAll(movie.getDirectors());
            actorList.addAll(movie.getCasts());
            actorAdapter.notifyDataSetChanged();
        }
    }

    private void blurBackground(String url) {
        Picasso.with(this).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Bitmap result = BlurUtil.apply(MovieDetailActivity.this, bitmap, 20);
                ivBlurBackground.setImageBitmap(result);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
}