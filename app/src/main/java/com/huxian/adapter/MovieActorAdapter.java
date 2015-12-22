package com.huxian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.R;
import com.huxian.model.Actor;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author huxian99
 */
public class MovieActorAdapter extends RecyclerView.Adapter<MovieActorAdapter.MovieActorHolder> {

    private Context context;
    private List<Actor> actorList;

    public MovieActorAdapter(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @Override
    public MovieActorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_movie_actor_item, parent, false);
        return new MovieActorHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieActorHolder holder, int position) {
        Actor actor = actorList.get(position);
        bindMovieActorHolder(actor, holder);
    }

    @Override
    public int getItemCount() {
        return actorList == null ? 0 : actorList.size();
    }

    private void bindMovieActorHolder(Actor actor, MovieActorHolder holder) {
        holder.tvName.setText(actor.getName());
        if (actor.getAvatars() != null && !TextUtils.isEmpty(actor.getAvatars().getLarge())) {
            Picasso.with(context).load(actor.getAvatars().getLarge()).into(holder.ivAvatar);
        }
    }

    static class MovieActorHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;

        public MovieActorHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_movie_actor_item_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_movie_actor_item_name);
        }
    }
}
