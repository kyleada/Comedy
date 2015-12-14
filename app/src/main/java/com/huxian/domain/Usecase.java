package com.huxian.domain;

import rx.Observable;

/**
 * @author huxian99
 */
public interface Usecase<T> {

    Observable<T> execute();
}
