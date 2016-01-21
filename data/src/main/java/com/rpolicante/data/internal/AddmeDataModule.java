package com.rpolicante.data.internal;

import android.content.Context;

import com.rpolicante.data.features.names.NamesDataRepository;
import com.rpolicante.data.features.names.datasources.disk.NamesDataSourceDisk;
import com.rpolicante.data.features.names.datasources.disk.realm.NamesDataSourceDiskRealm;
import com.rpolicante.data.features.names.datasources.disk.realm.entities.NamesDiskRealmMapper;
import com.rpolicante.domain.repository.NamesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 1/18/16.
 */
@Module
public class AddmeDataModule {
    private final Context context;

    public AddmeDataModule(Context context) {
        this.context = context;
    }

    //Repository

    @Provides
    @Singleton
    NamesRepository provideUserRepository(NamesDataRepository userRepository) {
        return userRepository;
    }

    //Data source

    @Provides
    @Singleton
    NamesDataSourceDisk provideNamesDataSourceDisk(NamesDiskRealmMapper namesDiskRealmMapper){
        return new NamesDataSourceDiskRealm(namesDiskRealmMapper);
    }

    // Mappers

    @Provides
    @Singleton
    NamesDiskRealmMapper provideNamesDiskMapper(){
        return new NamesDiskRealmMapper();
    }

}
