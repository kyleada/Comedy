package com.huxian.network;

import com.huxian.model.Actor;
import com.huxian.model.Movie;
import com.huxian.model.MovieFeed;

import rx.Observable;

/**
 * @author huxian99
 */
public interface Repository {

    Observable<MovieFeed> getMovieList(String list, int start, int count);

    Observable<Movie> getMovie(String movieId);

    Observable<Actor> getActor(String actorId);

}
