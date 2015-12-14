package com.huxian;

import android.app.Application;

import com.huxian.injector.component.ComedyComponent;
import com.huxian.injector.component.DaggerComedyComponent;
import com.huxian.injector.module.ComedyModule;

/**
 * @author huxian99
 */
public class ComedyApplication extends Application {

    private ComedyComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }

    private void setupGraph() {
        component = DaggerComedyComponent.builder()
                .comedyModule(new ComedyModule(this))
                .build();
        component.inject(this);
    }

    public ComedyComponent component() {
        return component;
    }

}
