package com.huxian.presenter;

import com.huxian.ui.view.IView;

/**
 * @author huxian99
 */
public interface BasePresenter {

    void onCreate();

    void attachView(IView view);

}
