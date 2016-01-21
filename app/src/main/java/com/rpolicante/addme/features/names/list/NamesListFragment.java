package com.rpolicante.addme.features.names.list;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseFragment;
import com.rpolicante.addme.features.names.adapter.NamesListAdapter;
import com.rpolicante.addme.features.names.add.NamesAddFragment;
import com.rpolicante.addme.helpers.AnimationHelpers;
import com.rpolicante.presentation.features.names.NamesListPresenter;
import com.rpolicante.presentation.features.names.NamesListView;
import com.rpolicante.presentation.model.NameModel;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Inject;

import butterknife.Bind;
import co.dift.ui.SwipeToAction;

/**
 * Created by policante on 1/19/16.
 */
public class NamesListFragment extends BaseFragment implements NamesListView, NamesAddFragment.OnAddNameListener {

    @Bind(R.id.recyclerview_nameslist)
    RecyclerView recyclerView;

    @Bind(R.id.include_empty_data)
    FrameLayout emptyData;

    @Bind(R.id.floating_action_button)
    FloatingActionButton fab;

    SwipeToAction swipeToAction;

    private NamesListAdapter adapter;

    @Inject
    NamesListPresenter namesListPresenter;

    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    @Override
    public void onResume() {
        super.onResume();
        namesListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        namesListPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        namesListPresenter.destroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_names_list;
    }

    @Override
    protected void initialize() {
        alertDialog = new AlertDialog.Builder(getContext())
                .setTitle(R.string.dialog_warning)
                .setNegativeButton(R.string.action_confirm, null)
                .create();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(R.string.loading_elipses);
        progressDialog.setIndeterminate(true);

        setupUI();
        setupSwipeAction();

        namesListPresenter.setView(this);
    }

    private void setupUI() {
        this.adapter = new NamesListAdapter(getContext(), new ArrayList<NameModel>());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.adapter);

        fab.setOnClickListener(onFABClickListener);
    }

    private void setupSwipeAction(){
        swipeToAction = new SwipeToAction(this.recyclerView, new SwipeToAction.SwipeListener<NameModel>() {

            @Override
            public boolean swipeLeft(NameModel itemData) {
                Log.d(TAG, "swipeLeft - detail");

                navigator.navigateToNameDetail(getContext(),itemData.getId());

                return true;
            }

            @Override
            public boolean swipeRight(NameModel itemData) {
                Log.d(TAG,"swipeRight - remove");
                removeList(itemData);
                return true;
            }

            @Override
            public void onClick(NameModel itemData) {
                Log.d(TAG,"onClick");
            }

            @Override
            public void onLongClick(NameModel itemData) {
                Log.d(TAG,"onLongClick");
            }
        });
    }

    private void removeList(final NameModel model) {
        adapter.removeNameModel(model);
        namesListPresenter.deleteName(model);
    }

    @Override
    protected void injectDependencies() {
        getComponent(NamesListComponent.class).inject(this);
    }

    @Override
    public void renderNamesList(Collection<NameModel> namesModelCollection) {
        this.adapter.setNamesCollection(namesModelCollection);
    }

    @Override
    public void renderName(NameModel nameModel) {
        this.adapter.addNameModel(0,nameModel);
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.hide();
    }

    @Override
    public void showError(String message) {
        alertDialog.setMessage(message);
        alertDialog.show();
    }

    private View.OnClickListener onFABClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NamesAddFragment add = new NamesAddFragment();
            add.setTargetFragment(NamesListFragment.this,0);
            FragmentManager fm = getFragmentManager();
            add.show(fm, "add");
        }
    };

    @Override
    public void showEmptyView() {
        AnimationHelpers.fade_GONE(recyclerView);
        AnimationHelpers.fade_VISIBLE(emptyData);
    }

    @Override
    public void hideEmptyView() {
        AnimationHelpers.fade_GONE(emptyData);
        AnimationHelpers.fade_VISIBLE(recyclerView);
    }

    @Override
    public void onAddName(NameModel model) {
        if (model != null){
            namesListPresenter.saveName(model);
        }
    }
}
