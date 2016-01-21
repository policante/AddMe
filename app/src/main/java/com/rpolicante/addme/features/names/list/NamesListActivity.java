package com.rpolicante.addme.features.names.list;

import android.content.Context;
import android.content.Intent;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseToolbar;


/**
 * Created by policante on 1/19/16.
 */
public class NamesListActivity extends BaseToolbar<NamesListComponent> {

    public static Intent getIntent(Context context) {
        return new Intent(context, NamesListActivity.class);
    }

    @Override
    protected int getToolbarResId() {
        return R.id.toolbar;
    }

    @Override
    protected int getToolbarTitleResId() {
        return R.string.app_name;
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_names_list;
    }

    @Override
    protected NamesListComponent getDaggerComponent() {
        return DaggerNamesListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

}
