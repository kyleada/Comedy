package com.huxian.injector.module;

import com.huxian.domain.GetActorDetailUsecase;
import com.huxian.injector.ActivityScope;
import com.huxian.network.Repository;

import dagger.Module;
import dagger.Provides;

/**
 * @author huxian99
 */
@Module
public class ActorDetailModule {

    private String actorId;

    public ActorDetailModule(String actorId) {
        this.actorId = actorId;
    }

    @Provides
    @ActivityScope
    GetActorDetailUsecase provideGetActorDetailUsecase(Repository repository) {
        return new GetActorDetailUsecase(repository, actorId);
    }

}
