package com.rpolicante.addme.features.names.detail;

import com.rpolicante.addme.internal.ActivityComponent;
import com.rpolicante.addme.internal.ActivityModule;
import com.rpolicante.addme.internal.ApplicationComponent;
import com.rpolicante.presentation.internal.PerActivity;

import dagger.Component;

/**
 * Created by policante on 1/21/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NameDetailModule.class})
public interface NameDetailComponent extends ActivityComponent{
    void inject(NameDetailFragment nameDetailFragment);
}
