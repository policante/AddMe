package com.rpolicante.presentation.features.names;

import android.util.Log;

import com.rpolicante.domain.Names;
import com.rpolicante.domain.features.names.NamesDeleteInteractor;
import com.rpolicante.domain.features.names.NamesListInteractor;
import com.rpolicante.domain.features.names.NamesSaveInteractor;
import com.rpolicante.domain.interactor.DefaultSubscriber;
import com.rpolicante.domain.interactor.UseCase;
import com.rpolicante.presentation.internal.PerActivity;
import com.rpolicante.presentation.model.NameModel;
import com.rpolicante.presentation.model.NameModelMapper;
import com.rpolicante.presentation.presenter.BasePresenter;
import com.rpolicante.presentation.presenter.ErrorPresenter;
import com.rpolicante.presentation.presenter.LoadPresenter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by policante on 1/19/16.
 */
@PerActivity
public class NamesListPresenter extends BasePresenter<NamesListView> implements LoadPresenter, ErrorPresenter{

    private final NamesListInteractor namesListInteractor;
    private final NamesDeleteInteractor namesDeleteInteractor;
    private final NamesSaveInteractor namesSaveInteractor;
    private final NameModelMapper nameModelMapper;

    @Inject
    public NamesListPresenter(@Named("listNames") UseCase namesListInteractor,
                              NamesDeleteInteractor namesDeleteInteractor,
                              @Named("saveNames") UseCase namesSaveInteractor,
                              NameModelMapper nameModelMapper){

        this.namesListInteractor = (NamesListInteractor) namesListInteractor;
        this.namesDeleteInteractor = namesDeleteInteractor;
        this.namesSaveInteractor = (NamesSaveInteractor) namesSaveInteractor;
        this.nameModelMapper = nameModelMapper;
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
        this.loadNames();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        namesListInteractor.unsubscribe();
        namesDeleteInteractor.unsubscribe();
        namesSaveInteractor.unsubscribe();
    }

    public void loadNames(){
        showViewLoading();
        this.namesListInteractor.execute(new DefaultSubscriber<List<Names>>() {
            @Override
            public void onCompleted() {
                NamesListPresenter.this.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                NamesListPresenter.this.hideViewLoading();
                NamesListPresenter.this.showViewError(e);
            }

            @Override
            public void onNext(List<Names> names) {
                Collection<NameModel> collectionName = new ArrayList<>();
                for (Names n : names) {
                    collectionName.add(nameModelMapper.modelToData(n));
                }

                NamesListPresenter.this.getView().renderNamesList(collectionName);

                if (collectionName.isEmpty()) {
                    NamesListPresenter.this.getView().showEmptyView();
                } else {
                    NamesListPresenter.this.getView().hideEmptyView();
                }

            }
        });
    }

    public void deleteName(NameModel name){
        showViewLoading();
        this.namesDeleteInteractor.setNames(nameModelMapper.dataToModel(name));
        this.namesDeleteInteractor.execute(new DefaultSubscriber<Boolean>(){
            @Override
            public void onCompleted() {
                NamesListPresenter.this.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                NamesListPresenter.this.hideViewLoading();
                NamesListPresenter.this.showViewError(e);
            }

            @Override
            public void onNext(Boolean isEmpty) {
                if (isEmpty){
                    NamesListPresenter.this.getView().showEmptyView();
                }else{
                    NamesListPresenter.this.getView().hideEmptyView();
                }
            }
        });
    }

    public void saveName(NameModel nameModel){
        showViewLoading();
        this.namesSaveInteractor.setName(nameModelMapper.dataToModel(nameModel));
        this.namesSaveInteractor.execute(new DefaultSubscriber<Names>(){
            @Override
            public void onCompleted() {
                NamesListPresenter.this.hideViewLoading();
            }

            @Override
            public void onError(Throwable e) {
                NamesListPresenter.this.hideViewLoading();
                NamesListPresenter.this.showViewError(e);
            }

            @Override
            public void onNext(Names names) {
                NameModel model = nameModelMapper.modelToData(names);
                NamesListPresenter.this.getView().renderName(model);
                NamesListPresenter.this.getView().hideEmptyView();
            }
        });
    }

    @Override
    public void showViewError(Throwable error) {

        Log.e(getClass().getSimpleName(),error.getMessage(), error);

        getView().showError(error.getMessage());
    }

}
