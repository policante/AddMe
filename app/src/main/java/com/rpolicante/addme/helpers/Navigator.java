package com.rpolicante.addme.helpers;

import android.content.Context;
import android.content.Intent;

import com.rpolicante.addme.features.names.detail.NameDetailActivity;
import com.rpolicante.addme.features.names.list.NamesListActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by policante on 1/18/16.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator(){

    }

    public void navigateToList(Context context) {
        if (context != null){
            Intent intent = NamesListActivity.getIntent(context);
            context.startActivity(intent);
        }
    }

    public void navigateToNameDetail(Context context, int identifier){
        if (context != null){
            context.startActivity( NameDetailActivity.getLaunchIntent(context, identifier) );
        }
    }
}
