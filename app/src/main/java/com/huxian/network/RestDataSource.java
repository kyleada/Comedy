package com.huxian.network;

import com.huxian.model.Actor;
import com.huxian.model.Movie;
import com.huxian.model.MovieFeed;
import com.huxian.util.Constant;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author huxian99
 */
public class RestDataSource implements Repository {

    private OpenApiService apiService;


    @Inject public RestDataSource() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder().connectTimeout
                (10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                                                  .baseUrl(Constant.BASE_URL)
                                                  .addConverterFactory(
                                                          GsonConverterFactory.create())
                                                  .addCallAdapterFactory(
                                                          RxJavaCallAdapterFactory
                                                                  .create())
                                                  .build();
        apiService = retrofit.create(OpenApiService.class);
    }


    @Override
    public Observable<MovieFeed> getMovieList(String list, int start, int count) {
        return apiService.getMovieList(list, start, count);
    }


    @Override public Observable<Movie> getMovie(String movieId) {
        return apiService.getMovie(movieId);
    }


    @Override public Observable<Actor> getActor(String actorId) {
        return apiService.getActor(actorId);
    }
}
