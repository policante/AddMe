package com.rpolicante.addme.internal;

import android.app.Activity;

import com.rpolicante.presentation.internal.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 1/18/16.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}
