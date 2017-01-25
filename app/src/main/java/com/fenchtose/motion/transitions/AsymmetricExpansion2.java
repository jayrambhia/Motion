package com.fenchtose.motion.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class AsymmetricExpansion2 extends Transition {

    public static final long DEFAULT_DURATION = 350;
    public static final long DEFAULT_START_DELAY = 50;

    private static final String PROPNAME_WIDTH = "motion:asymmetric2_expand:width";
    private static final String PROPNAME_HEIGHT = "motion:asymmetric2_expand:height";

    private static final String[] transitionProperties = {
            PROPNAME_WIDTH,
            PROPNAME_HEIGHT
    };
    private static final String TAG = "AsymmetricExpansion";

    private final int startWidth;
    private final int endWidth;
    private final int startHeight;
    private final int endHeight;
    private final int startDelay;

    public AsymmetricExpansion2(int startWidth, int endWidth, int startHeight, int endHeight, int startDelay) {
        this.startWidth = startWidth;
        this.endWidth = endWidth;
        this.startHeight = startHeight;
        this.endHeight = endHeight;
        this.startDelay = startDelay;
        setDuration(DEFAULT_DURATION);
    }

    @Override
    public String[] getTransitionProperties() {
        return transitionProperties;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_WIDTH, startWidth);
        transitionValues.values.put(PROPNAME_HEIGHT, startHeight);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        transitionValues.values.put(PROPNAME_WIDTH, endWidth);
        transitionValues.values.put(PROPNAME_HEIGHT, endHeight);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, final TransitionValues startValues,
                                   final TransitionValues endValues) {


        ValueAnimator x = ValueAnimator.ofInt(startWidth, endWidth);
        ValueAnimator y = ValueAnimator.ofInt(startHeight, endHeight);

//        endValues.view.setVisibility(View.INVISIBLE);
//        startValues.view.setVisibility(View.INVISIBLE);

        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams params = endValues.view.getLayoutParams();
                params.width = (int) valueAnimator.getAnimatedValue();
                endValues.view.setLayoutParams(params);
            }
        });

        y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams params = endValues.view.getLayoutParams();
                params.height = (int) valueAnimator.getAnimatedValue();
                endValues.view.setLayoutParams(params);
            }
        });

        x.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
//                endValues.view.setVisibility(View.VISIBLE);
//                startValues.view.setVisibility(View.VISIBLE);
                animator.removeListener(this);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        x.setDuration(getDuration());
        y.setDuration(getDuration());
        y.setStartDelay(startDelay);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(x, y);
        return set;
    }
}
