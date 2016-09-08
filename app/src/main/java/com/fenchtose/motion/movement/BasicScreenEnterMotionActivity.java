package com.fenchtose.motion.movement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;
import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicScreenEnterMotionActivity extends AppCompatActivity {

    private boolean isShown = false;

    @BindView(R.id.root) ViewGroup root;
    @BindView(R.id.cardview) CardView cardView;

    private int translationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_screen_enter_motion);
        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        translationY = DimensionUtils.dpToPx(156+24, density);

        root.setOnClickListener(new View.OnClickListener() {
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
        MotionAnimationUtils.animateY(cardView, 0, 250, new DecelerateInterpolator());
    }

    private void animateOut() {
        isShown = false;
        MotionAnimationUtils.animateY(cardView, translationY, 250, new AccelerateInterpolator());
    }
}
