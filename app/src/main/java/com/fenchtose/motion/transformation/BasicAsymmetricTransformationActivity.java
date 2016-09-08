package com.fenchtose.motion.transformation;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasicAsymmetricTransformationActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.root) ViewGroup root;

    private boolean isExpanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_asymmetric_transformation);
        ButterKnife.bind(this);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ViewGroup.LayoutParams params = cardView.getLayoutParams();

                if (!isExpanded) {
//                    params.height*=3;
//                    params.width*=3;

                    MotionAnimationUtils.expandAsymmetric(cardView, 3f, 3f, 320, 50);
                } else {
                    MotionAnimationUtils.contractAsymmetric(cardView, 0.33f, 0.33f, 320, 50);
//                    params.height/=3;
//                    params.width/=3;
                }

//                cardView.setLayoutParams(params);
//                TransitionManager.beginDelayedTransition(root, new ChangeBounds().setDuration(320).setInterpolator(new FastOutLinearInInterpolator()));
                isExpanded = !isExpanded;
            }
        });
    }

}
