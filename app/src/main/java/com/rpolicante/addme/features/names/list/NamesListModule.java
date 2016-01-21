package com.rpolicante.addme.features.names.list;

import com.rpolicante.domain.features.names.NamesListInteractor;
import com.rpolicante.domain.features.names.NamesSaveInteractor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.presentation.internal.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 1/19/16.
 */
@Module
public class NamesListModule {

    @Provides
    @PerActivity
    @Named("listNames")
    UseCase provideListNamesInteractor(NamesListInteractor namesListInteractor){
        return namesListInteractor;
    }

    @Provides
    @PerActivity
    @Named("saveNames")
    UseCase provideSaveNamesInteractor(NamesSaveInteractor namesSaveInteractor){
        return namesSaveInteractor;
    }

}
