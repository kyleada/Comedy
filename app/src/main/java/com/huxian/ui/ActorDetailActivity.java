package com.huxian.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.R;
import com.huxian.model.Actor;
import com.squareup.picasso.Picasso;

public class ActorDetailActivity extends BaseActivity {

    public static final String INTENT_ACTOR = "actor";

    private Actor actor;
    private String actorId;

    private ImageView ivAvatar;
    private TextView tvName;
    private TextView tvBornPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_detail);
        initData();
        initView();
    }

    private void initData() {
        actor = (Actor) getIntent().getSerializableExtra(INTENT_ACTOR);
        actorId = actor.getId();
    }

    private void initView() {
        ivAvatar = (ImageView) findViewById(R.id.iv_actor_detail_avatar);
        tvName = (TextView) findViewById(R.id.tv_actor_detail_name);
        tvBornPlace = (TextView) findViewById(R.id.tv_actor_detail_born_place);

        Picasso.with(this).load(actor.getAvatars().getMedium()).into(ivAvatar);
        tvName.setText(actor.getName());
    }

}
