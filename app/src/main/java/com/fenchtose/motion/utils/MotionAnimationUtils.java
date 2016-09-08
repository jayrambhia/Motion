package com.fenchtose.motion.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

/**
 * Created by Jay Rambhia on 9/8/16.
 */
public class MotionAnimationUtils {

    private static final String TAG = "MotionAnimationUtils";

    public static void animateY(@NonNull View view, int endY, int duration) {
        animateY(view, endY, duration, new AccelerateInterpolator());
    }

    public static void animateY(@NonNull View view, int endY, int duration,
                                @NonNull Interpolator interpolator) {

        ViewCompat.animate(view)
                .translationY(endY)
                .setInterpolator(interpolator)
                .setDuration(duration)
                .withLayer()
                .setListener(null)
                .start();
    }

    public static void animateX(@NonNull View view, int endX, int duration) {
        animateX(view, endX, duration, new DecelerateInterpolator());
    }

    public static void animateX(@NonNull View view, int endX, int duration,
                                @NonNull Interpolator interpolator) {

        ViewCompat.animate(view)
                .translationX(endX)
                .setInterpolator(interpolator)
                .setDuration(duration)
                .withLayer()
                .setListener(null)
                .start();
    }

    public static Animator animateScaleX(@NonNull View view, float finalScale, int duration) {

        return animateScaleX(view, finalScale, duration, new FastOutSlowInInterpolator());

    }

    public static Animator animateScaleX(@NonNull final View view, final float finalScale, int duration,
                                         @NonNull Interpolator interpolator) {


        Animator animator = ObjectAnimator.ofFloat(view, "scaleX", 1, finalScale)
                .setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.width *= finalScale;
                view.setLayoutParams(params);
                view.setScaleX(1);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        return animator;
    }

    public static Animator animateScaleY(@NonNull View view,float finalScale, int duration) {

        return animateScaleY(view, finalScale, duration, new FastOutSlowInInterpolator());

    }

    public static Animator animateScaleY(@NonNull final View view, final float finalScale, int duration,
                                         @NonNull Interpolator interpolator) {

        Animator animator = ObjectAnimator.ofFloat(view, "scaleY", 1, finalScale)
                .setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height *= finalScale;
                view.setLayoutParams(params);
                view.setScaleY(1);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        return animator;

    }

    public static void arcUp(@NonNull View view, int endX, int endY, int duration) {
        animateY(view, endY, duration, new AccelerateInterpolator());
        animateX(view, endX, duration, new DecelerateInterpolator());
    }

    public static void arcDown(@NonNull View view,int duration) {
        animateY(view, 0, duration, new DecelerateInterpolator());
        animateX(view, 0, duration, new AccelerateInterpolator());
    }

    public static void expandAsymmetric(@NonNull View view, float scaleX, float scaleY, int duration, int startDelay) {
        Animator x = animateScaleX(view, scaleX, duration);
        Animator y = animateScaleY(view, scaleY, duration);
        y.setStartDelay(startDelay);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(x, y);
        set.start();
    }

    public static void contractAsymmetric(@NonNull View view, float scaleX, float scaleY, int duration, int startDelay) {
        Animator x = animateScaleX(view, scaleX, duration);
        x.setStartDelay(startDelay);

        Animator y = animateScaleY(view, scaleY, duration);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(x, y);
        set.start();
    }

    public static void expandSymmetric(@NonNull View view, float scaleX, float scaleY, int duration) {
        expandAsymmetric(view, scaleX, scaleY, duration, 0);
    }

    public static void contractSymmetric(@NonNull View view, float scaleX, float scaleY, int duration) {
        contractAsymmetric(view, scaleX, scaleY, duration, 0);
    }

    public static Animator animateScaleX2(@NonNull final View view, final float finalScale, int duration,
                                         @NonNull Interpolator interpolator) {

        ViewGroup.LayoutParams params = view.getLayoutParams();
        int width = params.width;
        ValueAnimator animator = ValueAnimator.ofInt(width, (int)(width*finalScale));
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams cParams = view.getLayoutParams();
                cParams.width = value;
                view.setLayoutParams(cParams);
            }
        });

        return animator;
    }

    public static Animator animateScaleY2(@NonNull final View view, final float finalScale, int duration,
                                          @NonNull Interpolator interpolator) {

        ViewGroup.LayoutParams params = view.getLayoutParams();
        int height = params.height;
        ValueAnimator animator = ValueAnimator.ofInt(height, (int)(height*finalScale));
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams cParams = view.getLayoutParams();
                cParams.height = value;
                view.setLayoutParams(cParams);
            }
        });

        return animator;
    }

    public static void scaleAsymmetric2(@NonNull View view, float scaleX, float scaleY, int duration,
                                        int xStartDelay, int yStartDelay) {

        Animator x = animateScaleX2(view, scaleX, duration, new FastOutSlowInInterpolator());
        x.setStartDelay(xStartDelay);
        Animator y = animateScaleY2(view, scaleY, duration, new FastOutSlowInInterpolator());
        y.setStartDelay(yStartDelay);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(x, y);
        set.start();
    }

    public static void expandAsymmetric2(@NonNull View view, float scaleX, float scaleY, int duration,
                                         int xDelay, int yDelay) {
        scaleAsymmetric2(view, scaleX, scaleY, duration, xDelay, yDelay);
    }

    public static void contractAsymmetric2(@NonNull View view, float scaleX, float scaleY, int duration,
                                           int xDelay, int yDelay) {
        scaleAsymmetric2(view, scaleX, scaleY, duration, xDelay, yDelay);
    }

    public static void expandSymmetric2(@NonNull View view, float scaleX, float scaleY, int duration) {
        scaleAsymmetric2(view, scaleX, scaleY, duration, 0, 0);
    }

    public static void contractSymmetric2(@NonNull View view, float scaleX, float scaleY, int duration) {
        scaleAsymmetric2(view, scaleX, scaleY, duration, 0, 0);
    }

    public static void animateAlpha(@NonNull View view, float startAlpha, float endAlpha, int duration,
                                    int startDelay, @NonNull Interpolator interpolator) {

        Animator a = ObjectAnimator.ofFloat(view, "alpha", startAlpha, endAlpha);
        a.setStartDelay(startDelay);
        a.setInterpolator(interpolator);
        a.setDuration(duration);
        a.start();
    }

    public static void animateAlpha(@NonNull View view, float startAlpha, float endAlpha, int duration) {
        animateAlpha(view, startAlpha, endAlpha, duration, 0, new FastOutSlowInInterpolator());
    }

    public static void animateCircularReveal(@NonNull View view, int centerX, int centerY, int startRadius,
                                             int endRadius, int duration, int startDelay, @NonNull Interpolator interpolator) {

        Animator animator = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        animator.setStartDelay(startDelay);
        animator.setDuration(duration);
        animator.setInterpolator(interpolator);
        animator.start();
    }
}
