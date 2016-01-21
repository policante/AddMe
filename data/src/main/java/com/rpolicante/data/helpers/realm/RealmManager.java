package com.rpolicante.data.helpers.realm;

import android.content.Context;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by policante on 1/18/16.
 */
public class RealmManager {
    static final int SCHEMA_VERSION = 0;
    static final String SCHEMA_NAME = "AddMe.realm";

    private final Context context;
    private RealmConfiguration realmConfiguration;

    @Inject
    public RealmManager(Context context){
        this.context = context;
    }

    public void initialize(){
        realmConfiguration = new RealmConfiguration.Builder(context)
                .name(SCHEMA_NAME)
                .schemaVersion(SCHEMA_VERSION)
                .migration(new Migration())
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
