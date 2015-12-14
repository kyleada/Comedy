package com.huxian.presenter;

import com.huxian.domain.GetMovieListUsecase;
import com.huxian.model.Movie;
import com.huxian.model.MovieFeed;
import com.huxian.ui.view.IMovieListView;
import com.huxian.ui.view.IView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author huxian99
 */
public class MovieListPresenter implements BasePresenter {

    private GetMovieListUsecase movieListUsecase;
    private IMovieListView movieListView;
    private List<Movie> movieList;

    @Inject
    public MovieListPresenter(GetMovieListUsecase movieListUsecase) {
        this.movieListUsecase = movieListUsecase;
        this.movieList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        requestMovieList();
    }

    @Override
    public void attachView(IView view) {
        movieListView = (IMovieListView) view;
    }

    private void requestMovieList() {
        movieListView.showLoading();
        movieListUsecase.execute()
                .subscribe(new Subscriber<MovieFeed>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieFeed movieFeed) {
                        movieList.addAll(movieFeed.subjects);
                        movieListView.hideLoading();
                        movieListView.showMovieList(movieList);
                    }
                });
    }

}
