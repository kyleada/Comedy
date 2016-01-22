package com.huxian.domain;

import com.huxian.model.Actor;
import com.huxian.network.Repository;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author huxian99
 */
public class GetActorDetailUsecase implements Usecase<Actor> {

    private Repository repository;
    private String actorId;

    public GetActorDetailUsecase(Repository repository, String actorId) {
        this.repository = repository;
        this.actorId = actorId;
    }

    @Override
    public Observable<Actor> execute() {
        return repository.getActor(actorId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
