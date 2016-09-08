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

public class RadialArcTransformationActivity extends AppCompatActivity {

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.cardview) CardView cardView;

    private int translationY;
    private int translationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radial_arc_transformation);

        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        translationY = DimensionUtils.dpToPx(84 + 32, density);
        translationX = DimensionUtils.dpToPx(84 + 32, density);

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
        int radius = (int)Math.hypot(cardView.getWidth(), cardView.getHeight())/2;

        MotionAnimationUtils.animateAlpha(cardView, 0, 1, 60, 60, new FastOutSlowInInterpolator());
        MotionAnimationUtils.arcUp(cardView, -translationX, -translationY, 300);
        MotionAnimationUtils.arcUp(fab, -translationX, -translationY, 300);

        MotionAnimationUtils.animateCircularReveal(cardView, cardView.getWidth()/2, cardView.getHeight()/2,
                fab.getWidth()/2, radius, 300, 60, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateAlpha(fab, 1, 0, 120);
    }

    private void animateOut() {
        int radius = (int)Math.hypot(cardView.getWidth(), cardView.getHeight())/2;

        MotionAnimationUtils.animateAlpha(cardView, 1, 0, 300);
        MotionAnimationUtils.animateCircularReveal(cardView, cardView.getWidth()/2, cardView.getHeight()/2,
                radius, fab.getWidth()/2, 240, 30, new FastOutSlowInInterpolator());
        MotionAnimationUtils.arcDown(cardView, 240);
        MotionAnimationUtils.arcDown(fab, 240);
        MotionAnimationUtils.animateAlpha(fab, 0, 1, 240);
    }
}
