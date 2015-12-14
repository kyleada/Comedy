package com.huxian.injector.module;

import android.content.Context;

import com.huxian.domain.GetMovieListUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * @author huxian99
 */
@Module
public class MovieListModule {

    private Context context;
    private String list;

    public MovieListModule(Context context, String list) {
        this.context = context;
        this.list = list;
    }

    @Provides
    @ActivityScope
    GetMovieListUsecase provideGetMovieListUsecase (Repository repository) {
        return new GetMovieListUsecase(repository, list);
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }

}
