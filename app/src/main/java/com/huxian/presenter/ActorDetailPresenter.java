package com.huxian.presenter;

import com.huxian.domain.GetActorDetailUsecase;
import com.huxian.model.Actor;
import com.huxian.ui.view.IActorDetailView;
import com.huxian.ui.view.IView;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * @author huxian99
 */
public class ActorDetailPresenter implements BasePresenter {

    private IActorDetailView actorDetailView;
    private GetActorDetailUsecase getActorDetailUsecase;

    @Inject
    public ActorDetailPresenter(GetActorDetailUsecase getActorDetailUsecase) {
        this.getActorDetailUsecase = getActorDetailUsecase;
    }

    @Override
    public void onCreate() {
        requestActorDetail();
    }

    @Override
    public void attachView(IView view) {
        actorDetailView = (IActorDetailView) view;
    }

    private void requestActorDetail() {
        getActorDetailUsecase.execute()
                .subscribe(new Action1<Actor>() {
                    @Override
                    public void call(Actor actor) {
                        actorDetailView.showActor(actor);
                    }
                });
    }

}
