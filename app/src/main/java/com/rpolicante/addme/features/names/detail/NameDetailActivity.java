package com.rpolicante.addme.features.names.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseToolbar;

/**
 * Created by policante on 1/21/16.
 */
public class NameDetailActivity extends BaseToolbar<NameDetailComponent> {

    private static final String INTENT_EXTRA_PARAM_IDENTIFIER = NameDetailActivity.class.getPackage() + ".INTENT_EXTRA_PARAM_IDENTIFIER";

    public static Intent getLaunchIntent(final Context context, int identifier){
        Intent intent = new Intent(context, NameDetailActivity.class);
        intent.putExtra(INTENT_EXTRA_PARAM_IDENTIFIER, identifier);
        return intent;
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_name_detail;
    }

    @Override
    protected NameDetailComponent getDaggerComponent() {
        return DaggerNameDetailComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null){
            int nameIdentifier = getIntent().getIntExtra(INTENT_EXTRA_PARAM_IDENTIFIER, -1);
            addFragment(R.id.frame_layout, NameDetailFragment.newInstance(nameIdentifier));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar;
    }

    @Override
    protected int getToolbarTitleResId() {
        return R.string.app_name;
    }
}
