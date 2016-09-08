package com.fenchtose.motion.movement;

import android.graphics.Point;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;
import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicLinearMotionActivity extends AppCompatActivity {

    @BindView(R.id.cardview_horizontal) CardView horizontalView;
    @BindView(R.id.cardview_Vertical) CardView verticalView;

    private int horizontalRightMargin = 32;
    private int verticalTopMargin = 16;

    private boolean isHorizontalAtBase = true;
    private boolean isVerticalAtBase = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_linear_motion);
        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        horizontalRightMargin = DimensionUtils.dpToPx(horizontalRightMargin, density);
        verticalTopMargin = DimensionUtils.dpToPx(verticalTopMargin, density);

        final Point screen = new Point();
        getWindowManager().getDefaultDisplay().getSize(screen);

        /**
         * https://material.google.com/motion/duration-easing.html#duration-easing-natural-easing-curves
         * For movement within screen bounds, "Standard Curve" is the most common easing curve.
         */

        horizontalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int endX = isHorizontalAtBase ? screen.x - horizontalRightMargin - horizontalView.getWidth() : 0;
                MotionAnimationUtils.animateX(horizontalView, endX, 200, new FastOutSlowInInterpolator());
                isHorizontalAtBase = !isHorizontalAtBase;
            }
        });

        verticalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isVerticalAtBase) {
                    MotionAnimationUtils.animateY(verticalView, verticalTopMargin - (int)verticalView.getY(), 200, new AccelerateInterpolator());
                } else {
                    MotionAnimationUtils.animateY(verticalView, 0, 200, new FastOutSlowInInterpolator());
                }
            }
        });
    }
}
