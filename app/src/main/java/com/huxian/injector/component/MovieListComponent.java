package com.huxian.injector.component;

import android.content.Context;

import com.huxian.domain.GetMovieListUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.injector.module.MovieListModule;
import com.huxian.ui.fragment.MovieListFragment;

import dagger.Component;

/**
 * @author huxian99
 */
@ActivityScope
@Component(dependencies = ComedyComponent.class, modules = {MovieListModule.class})
public interface MovieListComponent extends ActivityComponent {

    void inject(MovieListFragment fragment);

    GetMovieListUsecase getMovieListUsecase();

    Context activityContext();

}
