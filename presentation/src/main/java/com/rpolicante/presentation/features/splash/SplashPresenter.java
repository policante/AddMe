package com.rpolicante.presentation.features.splash;

import android.os.Handler;

import com.rpolicante.presentation.internal.PerActivity;
import com.rpolicante.presentation.presenter.BasePresenter;
import com.rpolicante.presentation.presenter.LoadPresenter;

import javax.inject.Inject;

/**
 * Created by policante on 1/19/16.
 */
@PerActivity
public class SplashPresenter extends BasePresenter<SplashView> implements LoadPresenter {

    @Inject
    public SplashPresenter(){
        //Do nothing
    }

    @Override
    public void resume() {
        showViewLoading();
        initialize();
    }

    @Override
    public void pause() {
        //Do nothing
    }

    @Override
    public void destroy() {
        //Do nothing
    }

    private void initialize(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideViewLoading();
                getView().navigateToList();
            }
        }, 2000);
    }

    @Override
    public void showViewLoading() {
        getView().showLoading();
    }

    @Override
    public void hideViewLoading() {
        getView().hideLoading();
    }
}
