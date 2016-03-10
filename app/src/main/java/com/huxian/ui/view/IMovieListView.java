package com.huxian.ui.view;

import com.huxian.model.Movie;

import java.util.List;

/**
 * @author huxian99
 */
public interface IMovieListView extends IView {

    void showMovieList(List<Movie> movieList);

    void showLoading();

    void hideLoading();

    void showEmpty();

    void hideEmpty();

}
