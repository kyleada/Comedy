package com.huxian.injector.component;

import android.content.Context;

import com.huxian.injector.ActivityScope;
import com.huxian.injector.module.ActivityModule;

import dagger.Component;

/**
 * @author huxian99
 */
@ActivityScope
@Component(dependencies = ComedyComponent.class, modules = {ActivityModule.class})
public interface ActivityComponent {

    Context context();

}
