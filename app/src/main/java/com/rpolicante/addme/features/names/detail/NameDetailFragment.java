package com.rpolicante.addme.features.names.detail;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseFragment;
import com.rpolicante.presentation.features.detail.NameDetailPresenter;
import com.rpolicante.presentation.features.detail.NameDetailView;
import com.rpolicante.presentation.model.NameModel;

import javax.inject.Inject;

import butterknife.Bind;


/**
 * Created by policante on 1/21/16.
 */
public class NameDetailFragment extends BaseFragment implements NameDetailView {

    private static final String INTENT_EXTRA_PARAM_IDENTIFIER = NameDetailFragment.class.getPackage() + ".INTENT_EXTRA_PARAM_IDENTIFIER";
    private static final String INSTANCE_EXTRA_PARAM_IDENTIFIER = NameDetailActivity.class.getPackage() + ".INSTANCE_EXTRA_PARAM_IDENTIFIER";

    @Bind(R.id.textview_detail_title)
    TextView title;

    @Bind(R.id.textview_detail_description)
    TextView description;

    @Inject
    NameDetailPresenter presenter;

    private AlertDialog alertDialog;
    private ProgressDialog progressDialog;

    private int nameIdentifier;
    private NameModel model;

    public static NameDetailFragment newInstance(int nameIdentifier) {
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_EXTRA_PARAM_IDENTIFIER, nameIdentifier);

        NameDetailFragment fragment = new NameDetailFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_name_detail;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            this.nameIdentifier = bundle.getInt(INTENT_EXTRA_PARAM_IDENTIFIER);
        }else{
            this.nameIdentifier = savedInstanceState.getInt(INSTANCE_EXTRA_PARAM_IDENTIFIER);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (outState != null){
            outState.putInt(INSTANCE_EXTRA_PARAM_IDENTIFIER, this.nameIdentifier);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
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

        presenter.setView(this);
        presenter.initializer(this.nameIdentifier);
    }

    @Override
    protected void injectDependencies() {
        getComponent(NameDetailComponent.class).inject(this);
    }

    @Override
    public void renderName(NameModel nameModel) {
        this.model = nameModel;
        title.setText(nameModel.getName());
        description.setText(nameModel.getDescription());
    }

    @Override
    public void showError(String message) {
        alertDialog.setMessage(message);
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                getActivity().finish();
            }
        });
        alertDialog.show();
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {
            getActivity().finish();
        } else if (id == R.id.menu_action_delete){
            this.showConfirmDialog(getString(R.string.confirm_title_delete), getString(R.string.confirm_delete, this.model.getName()) , getString(R.string.action_delete), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.deleteName(model);
                    getActivity().finish();
                }
            });
        }else{
            return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void showConfirmDialog(String title, String message, String positive,DialogInterface.OnClickListener positiveListener){
        AlertDialog confirm = new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, positiveListener)
                .setNegativeButton(R.string.action_cancel, null)
                .create();

        confirm.show();
    }
}
