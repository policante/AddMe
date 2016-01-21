package com.rpolicante.addme.internal;

import android.app.Activity;

import com.rpolicante.addme.features.splash.SplashFragment;
import com.rpolicante.presentation.internal.PerActivity;

import dagger.Component;

/**
 * Created by policante on 1/18/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
