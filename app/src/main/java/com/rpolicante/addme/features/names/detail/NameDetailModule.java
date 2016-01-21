package com.rpolicante.addme.features.names.detail;

import com.rpolicante.domain.features.names.NamesDetailInteractor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.presentation.internal.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by policante on 1/21/16.
 */
@Module
public class NameDetailModule {
    @Provides
    @PerActivity
    @Named("detailName")
    UseCase provideDetailNamesInteractor(NamesDetailInteractor interactor){
        return interactor;
    }
}
