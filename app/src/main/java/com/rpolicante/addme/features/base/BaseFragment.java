package com.rpolicante.addme.features.base;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rpolicante.addme.R;
import com.rpolicante.addme.helpers.Navigator;
import com.rpolicante.addme.internal.ApplicationComponent;
import com.rpolicante.presentation.internal.HasComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;

/**
 * Created by policante on 1/19/16.
 */
public abstract class BaseFragment extends Fragment {

    protected static final String TAG = BaseFragment.class.getSimpleName();

    @Inject
    protected Navigator navigator;

    private Snackbar snackbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((BaseActivity)getActivity()).getApplicationComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        injectDependencies();
        initialize();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void injectViews(View view){
        ButterKnife.bind(this, view);
    }

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    protected View getActivityRootView() {
        View view = getActivity().findViewById(R.id.activity_coordinator_layout);
        return view;
    }

    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }

    protected void fragmentSlidingBack(){
        this.getFragmentManager().popBackStack();
    }

    protected void slidingToTop(@IdRes int containerView, Fragment fragment){
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_top, R.anim.slide_out_bottom, R.anim.slide_in_top, R.anim.slide_out_bottom);
        fragmentTransaction.add(containerView, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    protected Snackbar snackbarBuild(String message){
        snackbar = Snackbar.make(getActivityRootView(), message, Snackbar.LENGTH_LONG);
        return snackbar;
    }

    protected void hideSnackbar(){
        if (snackbar != null){
            snackbar.dismiss();
        }
        snackbar = null;
    }

    protected abstract @LayoutRes int getFragmentLayout();

    protected abstract void initialize();

    protected abstract void injectDependencies();
}
