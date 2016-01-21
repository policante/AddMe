package com.rpolicante.domain.features.names;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.interactor.PostExecutionThread;
import com.rpolicante.domain.interactor.ThreadExecutor;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.domain.repository.NamesRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by policante on 1/19/16.
 */
public class NamesListInteractor extends UseCase<List<Names>> {

    private final NamesRepository namesRepository;

    @Inject
    public NamesListInteractor(NamesRepository namesRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread){
        super(threadExecutor, postExecutionThread);
        this.namesRepository = namesRepository;
    }

    @Override
    protected Observable<List<Names>> buildUseCaseObservable() {
        return namesRepository.names();
    }
}
