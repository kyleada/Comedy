package com.huxian.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.huxian.R;
import com.huxian.model.Movie;
import com.huxian.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author huxian99
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {

    private Context context;
    private List<Movie> movieList;
    private LayoutInflater inflater;

    public MovieListAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MovieListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_movie_list_item, parent, false);
        return new MovieListHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MovieListHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.resetView(movie);
    }

    @Override
    public int getItemCount() {
        return movieList == null ? 0 : movieList.size();
    }

    static class MovieListHolder extends RecyclerView.ViewHolder {

        private Context context;
        private View rootView;
        private TextView tvTitle;
        private TextView tvOriginalTitle;
        private TextView tvCollectCount;
        private TextView tvRating;
        private ImageView ivPoster;

        public MovieListHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            rootView = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_movie_list_title);
            tvOriginalTitle = (TextView) itemView.findViewById(R.id.tv_movie_list_original_title);
            tvCollectCount = (TextView) itemView.findViewById(R.id.tv_movie_list_collect_count);
            tvRating = (TextView) itemView.findViewById(R.id.tv_movie_list_rating);
            ivPoster = (ImageView) itemView.findViewById(R.id.iv_movie_list_poster);
        }

        public void resetView(Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOriginalTitle.setText(movie.getOriginal_title());
            String collectCount = movie.getCollect_count() + context.getResources().getString(R.string.collect_count);
            tvCollectCount.setText(collectCount);
            if (movie.getRating() != null) {
                String average = movie.getRating().getAverage();
                tvRating.setText(Html.fromHtml("<font color='#ff4c4b'><big><big>" + average.charAt(0) + "</big></big></front>"
                        + "<font color='#ff4c4b'>" + average.substring(1) + "</font>"));
            } else {
                tvRating.setText(context.getResources().getString(R.string.no_score));
            }
            if (movie.getImages() != null && !TextUtils.isEmpty(movie.getImages().getLarge())) {
                Picasso.with(context).load(movie.getImages().getLarge()).into(ivPoster);
            }
            rootView.setTag(movie.getId());
            rootView.setOnClickListener(movieDetailClickListener);
        }

        private View.OnClickListener movieDetailClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String movieId = String.valueOf(view.getTag());
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.INTENT_MOVIE_ID, movieId);
                context.startActivity(intent);
            }
        };
    }

}
