package com.rpolicante.addme.features.base;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.rpolicante.addme.internal.ActivityComponent;

/**
 * Created by policante on 1/19/16.
 */
public abstract class BaseToolbar<C extends ActivityComponent> extends BaseActivity<C> {

    private Toolbar toolbar = null;

    protected abstract int getToolbarResId();

    protected abstract int getToolbarTitleResId();

    protected boolean hasToolbar() { return toolbar != null; }

    protected Toolbar getToolbar() { return toolbar; }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeToolbar();
    }

    private void initializeToolbar() {
        if (getToolbarResId() == 0) {
            return;
        }
        toolbar = (Toolbar) findViewById(getToolbarResId());
        if (hasToolbar()) {
            setSupportActionBar(toolbar);

            if (getToolbarTitleResId() > 0) {
                setTitle(getString(getToolbarTitleResId()));
            }
        }
    }
}
