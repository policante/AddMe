package com.rpolicante.presentation.presenter;

import android.support.annotation.NonNull;

import com.rpolicante.presentation.view.View;

/**
 * Created by policante on 1/19/16.
 */
public abstract class BasePresenter<V extends View> implements Presenter {

    private V view;

    public void setView(@NonNull V view){
        this.view = view;
    }

    public V getView(){
        return this.view;
    }

}
