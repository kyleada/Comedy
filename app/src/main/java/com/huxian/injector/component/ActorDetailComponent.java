package com.huxian.injector.component;

import com.huxian.domain.GetActorDetailUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.injector.module.ActivityModule;
import com.huxian.injector.module.ActorDetailModule;
import com.huxian.presenter.ActorDetailPresenter;
import com.huxian.ui.ActorDetailActivity;

import dagger.Component;

/**
 * @author huxian99
 */
@ActivityScope
@Component(dependencies = ComedyComponent.class, modules = {ActorDetailModule.class, ActivityModule.class})
public interface ActorDetailComponent extends ActivityComponent {

    void inject(ActorDetailActivity actorDetailActivity);

    GetActorDetailUsecase getActorDetailUsecase();

    ActorDetailPresenter actorDetailPresenter();

}
