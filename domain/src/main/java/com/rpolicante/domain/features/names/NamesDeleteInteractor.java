package com.rpolicante.domain.features.names;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.domain.repository.NamesRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 1/20/16.
 */
public class NamesDeleteInteractor extends UseCase<Boolean>{

    private final NamesRepository namesRepository;

    private Names name;

    @Inject
    public NamesDeleteInteractor(NamesRepository namesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.namesRepository = namesRepository;
    }

    public void setNames(Names name){
        this.name = name;
    }

    @Override
    protected Observable<Boolean> buildUseCaseObservable() {
        return namesRepository.delete(this.name);
    }
}
