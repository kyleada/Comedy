package com.huxian.injector.module;

import com.huxian.ComedyApplication;
import com.huxian.network.Repository;
import com.huxian.network.RestDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author huxian99
 */
@Module
public class ComedyModule {

    private ComedyApplication comedyApplication;

    public ComedyModule(ComedyApplication comedyApplication) {
        this.comedyApplication = comedyApplication;
    }

    @Provides
    @Singleton
    ComedyApplication provideApplication() {
        return comedyApplication;
    }

    @Provides
    @Singleton
    Repository provideDataRepository (RestDataSource restDataSource) {
        return restDataSource;
    }

}
