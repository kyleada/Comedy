package com.huxian.injector.component;

import com.huxian.domain.GetMovieDetailUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.injector.module.ActivityModule;
import com.huxian.injector.module.MovieDetailModule;
import com.huxian.ui.MovieDetailActivity;

import dagger.Component;

/**
 * @author huxian99
 */
@ActivityScope
@Component(dependencies = ComedyComponent.class, modules = {MovieDetailModule.class, ActivityModule.class})
public interface MovieDetailComponent extends ActivityComponent {

    void inject(MovieDetailActivity movieDetailActivity);

    GetMovieDetailUsecase getMovieDetailUsecase();

}
