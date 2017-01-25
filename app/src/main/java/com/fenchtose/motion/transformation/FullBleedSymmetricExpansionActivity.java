package com.fenchtose.motion.transformation;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeImageTransform;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullBleedSymmetricExpansionActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.root) ViewGroup root;

    private int selection = 0;
    private boolean isExpanded = false;

    @BindView(R.id.radio_group) RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_bleed_symmetric_expansion);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkId) {
                switch (checkId) {
                    case R.id.scale:
                        selection = 1;
                        break;
                    case R.id.transition:
                        selection = 2;
                        break;
                    case R.id.layout_params:
                        selection = 3;
                        break;
                    default:
                        selection = 3;
                        break;
                }
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (selection) {
                    case 1:
                        scale();
                        break;
                    case 2:
                        scene();
                        break;
                    case 3:
                        layoutParams();
                        break;
                    default:
                        scene();
                        break;
                }

                isExpanded = !isExpanded;
            }
        });
    }

    private void scale() {
        if (!isExpanded) {
            MotionAnimationUtils.expandAsymmetric(cardView, 3f, 1.5f, 320, 0);
        } else {
            MotionAnimationUtils.contractAsymmetric(cardView, 0.33f, 1/1.5f, 320, 0);
        }
    }

    private void scene() {
        ViewGroup.LayoutParams params = cardView.getLayoutParams();

        if (!isExpanded) {
            params.height*=1.5;
            params.width*=3;
        } else {
            params.height/=1.5f;
            params.width/=3;
        }

        cardView.setLayoutParams(params);
        TransitionManager.beginDelayedTransition(root, new ChangeBounds().setDuration(320).setInterpolator(new FastOutLinearInInterpolator()));
    }

    private void layoutParams() {
        if (!isExpanded) {
            MotionAnimationUtils.expandSymmetric2(cardView, 3f, 1.5f, 320);
        } else {
            MotionAnimationUtils.contractSymmetric2(cardView, 0.33f, 1/1.5f, 220);
        }
    }
}
