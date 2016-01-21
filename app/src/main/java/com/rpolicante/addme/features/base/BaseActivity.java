package com.rpolicante.addme.features.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentTransaction;

import com.rpolicante.addme.AddmeApplication;
import com.rpolicante.addme.helpers.Navigator;
import com.rpolicante.addme.internal.ActivityComponent;
import com.rpolicante.addme.internal.ActivityModule;
import com.rpolicante.addme.internal.ApplicationComponent;
import com.rpolicante.presentation.internal.HasComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by policante on 1/19/16.
 */
public abstract class BaseActivity<C extends ActivityComponent> extends AppCompatActivity implements HasComponent<C> {

    @Inject
    Navigator navigator;

    private C component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getActivityLayout());
        injectViews();

        this.getApplicationComponent().inject(this);
        this.initializerInjector();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected abstract @LayoutRes int getActivityLayout();

    protected void initializerInjector() {
        this.component = getDaggerComponent();
    }

    protected abstract C getDaggerComponent();

    private void injectViews() {
        ButterKnife.bind(this);
    }

    @Override
    public C getComponent() {
        return component;
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AddmeApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
