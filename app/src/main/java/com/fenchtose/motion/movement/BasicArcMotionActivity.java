package com.fenchtose.motion.movement;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;
import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicArcMotionActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;

    private int topMargin = 32;
    private int rightMargin = 16;

    private boolean isAtBase = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_arc);
        ButterKnife.bind(this);

        float density = getResources().getDisplayMetrics().density;
        topMargin = DimensionUtils.dpToPx(topMargin, density);
        rightMargin = DimensionUtils.dpToPx(rightMargin, density);

        final Point screen = new Point();
        getWindowManager().getDefaultDisplay().getSize(screen);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isAtBase) {
                    MotionAnimationUtils.arcUp(cardView, screen.x - (int)cardView.getX() - cardView.getWidth() - rightMargin,
                           topMargin - (int)cardView.getY(), 300);
                } else {
                    MotionAnimationUtils.arcDown(cardView, 300);
                }

                isAtBase = !isAtBase;
            }
        });
    }
}
