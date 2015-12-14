package com.huxian.injector.component;

import com.huxian.ComedyApplication;
import com.huxian.injector.module.ComedyModule;
import com.huxian.network.Repository;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author huxian99
 */
@Singleton
@Component(
        modules = {
                ComedyModule.class
        }
)
public interface ComedyComponent {

    void inject(ComedyApplication comedyApplication);

    ComedyApplication application();

    Repository repository();

}
