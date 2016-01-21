package com.rpolicante.addme.internal;

import android.content.Context;

import com.rpolicante.addme.AddmeApplication;
import com.rpolicante.addme.helpers.Navigator;
import com.rpolicante.data.internal.AddmeDataModule;
import com.rpolicante.domain.features.names.NamesDeleteInteractor;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by policante on 1/18/16.
 */
@Module(includes = AddmeDataModule.class)
public class ApplicationModule {

    private final AddmeApplication application;

    public ApplicationModule(AddmeApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    Navigator provideNavigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread() {
        return new PostExecutionThread() {
            @Override
            public Scheduler getScheduler() {
                //UI Thread
                return AndroidSchedulers.mainThread();
            }
        };
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor() {
        return new ThreadExecutor() {
            @Override
            public Scheduler getScheduler() {
                //new Thread
                return Schedulers.newThread();
            }
        };
    }

    @Provides
    @Singleton
    NamesDeleteInteractor provideDeleteNamesInteractor(NamesDeleteInteractor namesDeleteInteractor){
        return namesDeleteInteractor;
    }
}
