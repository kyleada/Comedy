package com.huxian.injector.module;

import android.content.Context;

import com.huxian.injector.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author huxian99
 */
@Module
public class ActivityModule {

    private Context context;

    public ActivityModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    Context provideContext() {
        return context;
    }

}
