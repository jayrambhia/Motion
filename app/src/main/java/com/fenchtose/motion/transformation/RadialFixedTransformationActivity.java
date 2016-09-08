package com.fenchtose.motion.transformation;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadialFixedTransformationActivity extends AppCompatActivity {

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.cardview) CardView cardView;

    int radiusDiff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radial_fixed_transformation);
        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        radiusDiff = DimensionUtils.dpToPx(28+32, density);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateIn();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateOut();
            }
        });

        cardView.setAlpha(0);
    }

    private void animateIn() {
        int radius = (int)Math.hypot(cardView.getWidth(), cardView.getHeight());

        MotionAnimationUtils.animateAlpha(cardView, 0, 1, 60, 60, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateCircularReveal(cardView, cardView.getWidth()-radiusDiff, cardView.getHeight() - radiusDiff,
                fab.getWidth()/2, radius, 300, 60, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateAlpha(fab, 1, 0, 120);
    }

    private void animateOut() {
        int radius = (int)Math.hypot(cardView.getWidth(), cardView.getHeight());

        MotionAnimationUtils.animateAlpha(cardView, 1, 0, 240, 60, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateCircularReveal(cardView, cardView.getWidth() - radiusDiff, cardView.getHeight() - radiusDiff,
                radius, fab.getWidth()/2, 240, 0, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateAlpha(fab, 0, 1, 240);
    }
}
