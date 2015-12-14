package com.huxian.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.huxian.network.OpenApiService;
import com.huxian.util.Constant;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * @author huxian99
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
