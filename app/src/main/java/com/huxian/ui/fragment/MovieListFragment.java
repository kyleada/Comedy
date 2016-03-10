package com.huxian.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.huxian.ComedyApplication;
import com.huxian.R;
import com.huxian.adapter.MovieListAdapter;
import com.huxian.injector.component.DaggerMovieListComponent;
import com.huxian.injector.module.MovieListModule;
import com.huxian.model.Movie;
import com.huxian.presenter.MovieListPresenter;
import com.huxian.ui.view.IMovieListView;
import com.huxian.ui.widget.KSwipeRefreshLayout;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * @author huxian99
 */
public class MovieListFragment extends BaseFragment implements
        IMovieListView,SwipeRefreshLayout.OnRefreshListener {

    public static final String API_LIST = "list";

    @Inject MovieListPresenter movieListPresenter;

    private Activity activity;
    private KSwipeRefreshLayout kSwipeRefreshLayout;
    private RecyclerView rvMovieList;
    private View emptyView;
    private ProgressBar progressBar;
    private LinearLayoutManager layoutManager;
    private MovieListAdapter listAdapter;



    private String apiList;
    private List<Movie> movieList;

    public static MovieListFragment newInstance(String title) {
        MovieListFragment fragment = new MovieListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(API_LIST, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        apiList = getArguments().getString(API_LIST);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initDependencyInjector();
        initPresenter();
    }

    @Override
    public void showMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        this.listAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        kSwipeRefreshLayout.setRefreshing(false);
    }


    @Override public void showEmpty() {
        this.movieList.clear();
        this.listAdapter.notifyDataSetChanged();
        emptyView.setVisibility(View.VISIBLE);
    }


    @Override public void hideEmpty() {
        emptyView.setVisibility(View.GONE);
    }


    private void initView(View view) {
        kSwipeRefreshLayout = (KSwipeRefreshLayout)view.findViewById(R.id.swipe);
        kSwipeRefreshLayout.setOnRefreshListener(this);
        rvMovieList = (RecyclerView) view.findViewById(R.id.rv_movie_list);
        progressBar = (ProgressBar) view.findViewById(R.id.pb_movie_list);
        emptyView = view.findViewById(R.id.tv_empty);
        layoutManager = new LinearLayoutManager(activity);
        rvMovieList.setLayoutManager(layoutManager);
        movieList = new ArrayList<>();
        listAdapter = new MovieListAdapter(activity, movieList);
        rvMovieList.setAdapter(listAdapter);
    }

    private void initDependencyInjector() {
        ComedyApplication comedyApplication = (ComedyApplication) activity.getApplication();
        DaggerMovieListComponent.builder()
                .movieListModule(new MovieListModule(activity, apiList))
                .comedyComponent(comedyApplication.component())
                .build()
                .inject(this);
    }

    private void initPresenter() {
        movieListPresenter.attachView(this);
        movieListPresenter.onCreate();
    }


    @Override public void onRefresh() {
        kSwipeRefreshLayout.setRefreshing(true);
        movieListPresenter.onCreate();
    }
}
