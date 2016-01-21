package com.rpolicante.domain.features.names;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.domain.repository.NamesRepository;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by policante on 1/20/16.
 */
public class NamesSaveInteractor extends UseCase<Names> {

    private final NamesRepository namesRepository;

    private Names name;

    @Inject
    public NamesSaveInteractor(NamesRepository namesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.namesRepository = namesRepository;
    }

    public void setName(Names name){
        this.name = name;
    }

    @Override
    protected Observable<Names> buildUseCaseObservable() {
        return this.namesRepository.save(this.name).flatMap(new Func1<Integer, Observable<? extends Names>>() {
            @Override
            public Observable<? extends Names> call(Integer identifier) {
                return namesRepository.getName(identifier);
            }
        });
    }
}
