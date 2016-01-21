package com.rpolicante.addme.helpers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

/**
 * Created by policante on 1/21/16.
 */
public class AnimationHelpers {

    public static void fade_GONE(final View view){
        fade_GONE(view, 500);
    }

    public static void fade_GONE(final View view, int duration){
        view.animate()
                .alpha(0.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }

    public static void fade_VISIBLE(final View view){
        fade_VISIBLE(view, 500);
    }

    public static void fade_VISIBLE(final View view, int duration){
        view.animate()
                .alpha(100.0f)
                .setDuration(duration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.VISIBLE);
                    }
                });
    }

}
