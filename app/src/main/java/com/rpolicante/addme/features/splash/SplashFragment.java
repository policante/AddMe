package com.rpolicante.addme.features.splash;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rpolicante.addme.R;
import com.rpolicante.addme.features.base.BaseFragment;
import com.rpolicante.presentation.features.splash.SplashPresenter;
import com.rpolicante.presentation.features.splash.SplashView;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Created by policante on 1/19/16.
 */
public class SplashFragment extends BaseFragment implements SplashView {

    @Bind(R.id.textview_splash_loading)
    TextView splash_loading;
    @Bind(R.id.progress_splash_loading)
    ProgressBar progress;

    @Inject
    SplashPresenter presenter;

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
    protected int getFragmentLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initialize() {
        presenter.setView(this);
    }

    @Override
    protected void injectDependencies() {
        getComponent(SplashComponent.class).inject(this);
    }

    @Override
    public void navigateToList() {
        navigator.navigateToList(getActivity());
        getActivity().finish();
    }

    @Override
    public void showLoading() {
        splash_loading.setVisibility(View.VISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        splash_loading.setVisibility(View.GONE);
        progress.setVisibility(View.GONE);
    }
}
