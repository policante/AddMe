package com.rpolicante.addme.features.names.list;

import com.rpolicante.addme.internal.ActivityComponent;
import com.rpolicante.addme.internal.ActivityModule;
import com.rpolicante.addme.internal.ApplicationComponent;
import com.rpolicante.presentation.internal.PerActivity;

import dagger.Component;

/**
 * Created by policante on 1/19/16.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, NamesListModule.class})
public interface NamesListComponent extends ActivityComponent{

    void inject(NamesListFragment namesListFragment);
}
