package com.huxian.presenter;

import android.content.Context;

import com.huxian.domain.GetMovieDetailUsecase;
import com.huxian.model.Movie;
import com.huxian.ui.view.IMovieDetailView;
import com.huxian.ui.view.IView;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * @author huxian99
 */
public class MovieDetailPresenter implements BasePresenter {

    private IMovieDetailView movieDetailView;
    private GetMovieDetailUsecase getMovieDetailUsecase;

    @Inject
    public MovieDetailPresenter(GetMovieDetailUsecase getMovieDetailUsecase) {
        this.getMovieDetailUsecase = getMovieDetailUsecase;
    }

    @Override
    public void onCreate() {
        requestMovieDetail();
    }

    @Override
    public void attachView(IView view) {
        movieDetailView = (IMovieDetailView) view;
    }

    private void requestMovieDetail() {
        getMovieDetailUsecase.execute()
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Movie movie) {
                        movieDetailView.showMovie(movie);
                    }
                });
    }

}
