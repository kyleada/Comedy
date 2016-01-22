package com.huxian.network;

import com.huxian.model.Actor;
import com.huxian.model.Movie;
import com.huxian.model.MovieFeed;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * @author huxian99
 */
public interface OpenApiService {

    @GET("movie/{list}")
    Observable<MovieFeed> getMovieList(@Path("list") String list, @Query("start") int start, @Query("count") int count);

    @GET("movie/search")
    Observable<MovieFeed> getSearchList(@Query("q") String searchText);

    @GET("movie/subject/{movieId}")
    Observable<Movie> getMovie(@Path("movieId") String movieId);

    @GET("movie/celebrity/{actorId}")
    Observable<Actor> getActor(@Path("actorId") String actorId);

}
