package com.rpolicante.addme;

import android.app.Application;

import com.rpolicante.addme.internal.ApplicationComponent;
import com.rpolicante.addme.internal.ApplicationModule;
import com.rpolicante.addme.internal.DaggerApplicationComponent;
import com.rpolicante.data.helpers.realm.RealmManager;
import com.rpolicante.data.internal.AddmeDataModule;

import javax.inject.Inject;

/**
 * Created by policante on 1/18/16.
 */
public class AddmeApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Inject
    RealmManager realmManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();

        getApplicationComponent().inject(this);
        initializeRealm();
    }

    private void initializeRealm() {
        realmManager.initialize();
    }

    protected void initializeInjector(){
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .addmeDataModule(new AddmeDataModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return this.applicationComponent;
    }

}
