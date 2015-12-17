package com.huxian.injector.module;

import com.huxian.domain.GetMovieDetailUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * @author huxian99
 */
@Module
public class MovieDetailModule {

    private String movieId;

    public MovieDetailModule(String movieId) {
        this.movieId = movieId;
    }

    @Provides
    @ActivityScope
    GetMovieDetailUsecase provideGetMovieDetailUsecase(Repository repository) {
        return new GetMovieDetailUsecase(repository, movieId);
    }

}
