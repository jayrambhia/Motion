package com.fenchtose.motion.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.PathInterpolator;

import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;
import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicScreenTemporaryExitMovementActivity extends AppCompatActivity {

    private boolean isShown = true;

    @BindView(R.id.root) ViewGroup root;
    @BindView(R.id.cardview) CardView cardView;

    private int translationX;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen_temporary_exit_movement);
        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        translationX = DimensionUtils.dpToPx(-240, density);

        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isShown) {
                    animateIn();
                }
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateOut();
            }
        });
    }

    private void animateIn() {
        isShown = true;
        MotionAnimationUtils.animateX(cardView, 0, 250, new DecelerateInterpolator());
    }

    private void animateOut() {
        isShown = false;
        MotionAnimationUtils.animateX(cardView, translationX, 250, new PathInterpolator(0.4f, 0, 0.6f, 1));
    }
}
