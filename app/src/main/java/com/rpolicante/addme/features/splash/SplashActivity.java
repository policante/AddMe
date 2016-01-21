package com.rpolicante.addme.features.splash;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseActivity;

/**
 * Created by policante on 1/19/16.
 */
public class SplashActivity extends BaseActivity<SplashComponent>{

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected SplashComponent getDaggerComponent() {
        return DaggerSplashComponent.builder()
                .activityModule(getActivityModule())
                .applicationComponent(getApplicationComponent())
                .build();
    }

}
