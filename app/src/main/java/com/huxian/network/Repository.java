package com.huxian.network;

import com.huxian.model.MovieFeed;

import rx.Observable;

/**
 * @author huxian99
 */
public interface Repository {

    Observable<MovieFeed> getMovieList(String list, int start, int count);

}
