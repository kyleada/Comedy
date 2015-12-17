package com.huxian.domain;

import com.huxian.model.Movie;
import com.huxian.network.Repository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author huxian99
 */
public class GetMovieDetailUsecase implements Usecase<Movie> {

    private Repository repository;
    private String movieId;

    @Inject
    public GetMovieDetailUsecase(Repository repository, String movieId) {
        this.repository = repository;
        this.movieId = movieId;
    }

    @Override
    public Observable<Movie> execute() {
        return repository.getMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
