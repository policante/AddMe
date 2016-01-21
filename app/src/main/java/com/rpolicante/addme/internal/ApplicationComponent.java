package com.rpolicante.addme.internal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rpolicante.addme.AddmeApplication;
import com.rpolicante.addme.features.base.BaseFragment;
import com.rpolicante.addme.helpers.Navigator;
import com.rpolicante.data.internal.AddmeDataComponent;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by policante on 1/18/16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent extends AddmeDataComponent{

    void inject(AddmeApplication application);
    void inject(AppCompatActivity activity);
    void inject(BaseFragment fragment);

    Context context();
    Navigator navigator();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
}
