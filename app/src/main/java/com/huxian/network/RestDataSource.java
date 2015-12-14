package com.huxian.network;

import com.huxian.model.MovieFeed;
import com.huxian.util.Constant;

import javax.inject.Inject;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * @author huxian99
 */
public class RestDataSource implements Repository {

    private OpenApiService apiService;

    @Inject
    public RestDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(OpenApiService.class);
    }

    @Override
    public Observable<MovieFeed> getMovieList(String list, int start, int count) {
        return apiService.getMovieList(list, start, count);
    }
}
