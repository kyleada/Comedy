package com.huxian.domain;

import com.huxian.model.MovieFeed;
import com.huxian.network.Repository;

import javax.inject.Inject;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author huxian99
 */
public class GetMovieListUsecase implements Usecase<MovieFeed> {

    public static final int COUNT = 20;

    private Repository repository;
    private String list;

    public GetMovieListUsecase(Repository repository, String list) {
        this.repository = repository;
        this.list = list;
    }

    @Override
    public Observable<MovieFeed> execute() {
        return repository.getMovieList(list, 0, COUNT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
