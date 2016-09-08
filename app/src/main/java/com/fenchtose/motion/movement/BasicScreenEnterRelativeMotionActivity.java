package com.fenchtose.motion.movement;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;
import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicScreenEnterRelativeMotionActivity extends AppCompatActivity {

    private boolean isShown = false;

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.fab) FloatingActionButton fab;

    private int translationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen_enter_relative_motion);

        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        translationY = DimensionUtils.dpToPx(156+24, density);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShown) {
                    animateUp();
                }
            }
        });

        cardView.setTranslationY(translationY);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateOut();
            }
        });
    }

    private void animateUp() {
        isShown = true;
        MotionAnimationUtils.animateY(cardView, 0, 250, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateY(fab, -translationY, 250, new FastOutSlowInInterpolator());

    }

    private void animateOut() {
        isShown = false;
        MotionAnimationUtils.animateY(cardView, translationY, 250, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateY(fab, 0, 250, new FastOutSlowInInterpolator());

    }
}
