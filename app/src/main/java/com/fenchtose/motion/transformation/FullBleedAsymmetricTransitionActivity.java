package com.fenchtose.motion.transformation;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullBleedAsymmetricTransitionActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.imageview) ImageView imageView;
    @BindView(R.id.content1) View content1;
    @BindView(R.id.content2) View content2;

    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_bleed_asymmetric_transition);
        ButterKnife.bind(this);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExpanded) {
                    expand();
                } else {
                    collapse();
                }

                isExpanded = !isExpanded;
            }
        });

        content1.setAlpha(0);
        content2.setAlpha(0);
    }

    private void expand() {
        MotionAnimationUtils.expandAsymmetric2(cardView, 3f, 2.5f, 375, 0, 75);
        MotionAnimationUtils.expandSymmetric2(imageView, 3f, 1.5f, 375);
        MotionAnimationUtils.animateAlpha(content1, 0, 1, 150, 75, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateAlpha(content2, 0, 1, 150, 75, new FastOutSlowInInterpolator());
    }

    private void collapse() {
        MotionAnimationUtils.contractAsymmetric2(cardView, 1/3f, 1/2.5f, 375, 75, 0);
        MotionAnimationUtils.contractAsymmetric2(imageView, 1/3f, 1/1.5f, 375, 75, 40);
        MotionAnimationUtils.animateAlpha(content1, 1, 0, 75, 0, new FastOutSlowInInterpolator());
        MotionAnimationUtils.animateAlpha(content2, 1, 0, 75, 0, new FastOutSlowInInterpolator());
    }
}
