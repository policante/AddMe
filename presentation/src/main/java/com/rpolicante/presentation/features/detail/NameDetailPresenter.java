package com.rpolicante.presentation.features.detail;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.features.names.NamesDeleteInteractor;
import com.rpolicante.domain.features.names.NamesDetailInteractor;
import com.rpolicante.domain.interactor.DefaultSubscriber;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.presentation.model.NameModel;
import com.rpolicante.presentation.model.NameModelMapper;
import com.rpolicante.presentation.presenter.BasePresenter;
import com.rpolicante.presentation.presenter.ErrorPresenter;
import com.rpolicante.presentation.presenter.LoadPresenter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by policante on 1/21/16.
 */
public class NameDetailPresenter extends BasePresenter<NameDetailView> implements LoadPresenter, ErrorPresenter {

    private final NamesDetailInteractor interactor;
    private final NamesDeleteInteractor deleteInteractor;
    private final NameModelMapper nameModelMapper;

    @Inject
    public NameDetailPresenter(@Named("detailName") UseCase namesDetailInteractor,
                               NamesDeleteInteractor namesDeleteInteractor,
                               NameModelMapper mapper){
        this.interactor = (NamesDetailInteractor) namesDetailInteractor;
        this.deleteInteractor = namesDeleteInteractor;
        this.nameModelMapper = mapper;
    }

    @Override
    public void showViewError(Throwable error) {
        getView().showError(error.getMessage());
    }

    @Override
    public void showViewLoading() {
        getView().showLoading();
    }

    @Override
    public void hideViewLoading() {
        getView().hideLoading();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        interactor.unsubscribe();
        deleteInteractor.unsubscribe();
    }

    public void initializer(int identifier){
        showViewLoading();
        this.interactor.setIdentifier(identifier);
        this.interactor.execute(new DefaultSubscriber<Names>() {
            @Override
            public void onCompleted() {
                NameDetailPresenter.this.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                NameDetailPresenter.this.hideViewLoading();
                NameDetailPresenter.this.showViewError(e);
            }

            @Override
            public void onNext(Names names) {
                NameModel model = nameModelMapper.modelToData(names);
                NameDetailPresenter.this.getView().renderName(model);
            }
        });
    }

    public void deleteName(NameModel name){
        showViewLoading();
        this.deleteInteractor.setNames(nameModelMapper.dataToModel(name));
        this.deleteInteractor.execute(new DefaultSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                NameDetailPresenter.this.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                NameDetailPresenter.this.hideViewLoading();
                NameDetailPresenter.this.showViewError(e);
            }
        });
    }
}
