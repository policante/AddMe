package com.rpolicante.domain.features.names;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.domain.repository.NamesRepository;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 1/21/16.
 */
public class NamesDetailInteractor extends UseCase<Names> {

    private final NamesRepository repository;

    private int identifier;

    @Inject
    public NamesDetailInteractor(NamesRepository namesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.repository = namesRepository;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    protected Observable<Names> buildUseCaseObservable() {
        return this.repository.getName(this.identifier);
    }
}
