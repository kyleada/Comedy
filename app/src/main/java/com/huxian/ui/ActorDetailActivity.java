package com.huxian.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.ComedyApplication;
import com.huxian.R;
import com.huxian.injector.component.DaggerActorDetailComponent;
import com.huxian.injector.module.ActivityModule;
import com.huxian.injector.module.ActorDetailModule;
import com.huxian.model.Actor;
import com.huxian.presenter.ActorDetailPresenter;
import com.huxian.ui.view.IActorDetailView;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ActorDetailActivity extends BaseActivity implements IActorDetailView {

    public static final String INTENT_ACTOR = "actor";

    @Inject
    ActorDetailPresenter actorDetailPresenter;

    private Actor actor;
    private String actorId;

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvEnglishName;
    private TextView tvBornPlace;
    private TextView tvGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);
        initData();
        initView();
        initDependencyInjector();
        initPresenter();
    }

    private void initData() {
        actor = (Actor) getIntent().getSerializableExtra(INTENT_ACTOR);
        actorId = actor.getId();
    }

    private void initView() {
        ivAvatar = (ImageView) findViewById(R.id.iv_actor_detail_avatar);
        tvName = (TextView) findViewById(R.id.tv_actor_detail_name);
        tvEnglishName = (TextView) findViewById(R.id.tv_actor_detail_english_name);
        tvBornPlace = (TextView) findViewById(R.id.tv_actor_detail_born_place);
        tvGender = (TextView) findViewById(R.id.tv_actor_detail_gender);

        Picasso.with(this).load(actor.getAvatars().getMedium()).into(ivAvatar);
        tvName.setText(actor.getName());
    }

    private void initDependencyInjector() {
        ComedyApplication comedyApplication = (ComedyApplication) getApplication();
        DaggerActorDetailComponent.builder()
                .comedyComponent(comedyApplication.component())
                .actorDetailModule(new ActorDetailModule(actorId))
                .activityModule(new ActivityModule(this))
                .build()
                .inject(this);
    }

    private void initPresenter() {
        actorDetailPresenter.attachView(this);
        actorDetailPresenter.onCreate();
    }

    @Override
    public void showActor(Actor actor) {
        tvEnglishName.setText(actor.getName_en());
        tvBornPlace.setText(actor.getBorn_place());
        tvGender.setText(actor.getGender());
    }
}
