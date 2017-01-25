package com.fenchtose.motion.transitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class AsymmetricCollapse2 extends Transition {

    public static final long DEFAULT_DURATION = 350;
    public static final long DEFAULT_START_DELAY = 50;

    private static final String PROPNAME_WIDTH = "motion:asymmetric2_collapse:width";
    private static final String PROPNAME_HEIGHT = "motion:asymmetric2_collapse:height";

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

    public AsymmetricCollapse2(int startWidth, int endWidth, int startHeight, int endHeight, int startDelay) {
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

        x.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams params = startValues.view.getLayoutParams();
                params.width = (int) valueAnimator.getAnimatedValue();
                startValues.view.setLayoutParams(params);
                Log.d(TAG, "value: " + valueAnimator.getAnimatedValue());
            }
        });

        y.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams params = startValues.view.getLayoutParams();
                params.height = (int) valueAnimator.getAnimatedValue();
                startValues.view.setLayoutParams(params);
            }
        });

        x.setDuration(getDuration());
        y.setDuration(getDuration());
        x.setStartDelay(startDelay);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(y, x);
        return set;
    }
}
