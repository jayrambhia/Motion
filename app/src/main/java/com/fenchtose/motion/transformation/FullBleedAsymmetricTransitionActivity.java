package com.fenchtose.motion.transformation;

import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.transition.ChangeBounds;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FullBleedAsymmetricTransitionActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.imageview) ImageView imageView;
    @BindView(R.id.content1) View content1;
    @BindView(R.id.content2) View content2;
    @BindView(R.id.root) ViewGroup root;
    @BindView(R.id.radio_group) RadioGroup radioGroup;

    private boolean isExpanded = false;
    private int selection = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_bleed_asymmetric_transition);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selection = i;
                Log.d("seletion: ", ""+i);
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isExpanded) {
                    if (selection == 1) {
                        expandTransition();
                    } else {
                        expand();
                    }

                } else {
                    if (selection == 1) {
                        collapseTransition();
                    } else {
                        collapse();
                    }
                }

                isExpanded = !isExpanded;
            }
        });

        content1.setVisibility(View.GONE);
        content2.setVisibility(View.GONE);
    }

    private void expand() {
        content1.setVisibility(View.VISIBLE);
        content2.setVisibility(View.VISIBLE);

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

    private void expandTransition() {

        Transition imageTransition = new ChangeImageTransform();
        imageTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(375);
        imageTransition.addTarget(imageView);

        Transition cardTransition = new ChangeBounds();
        cardTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(375);
        cardTransition.addTarget(cardView).addTarget(imageView);

        Transition alphaTransition = new Fade();
        alphaTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(150)
                .setStartDelay(75)
                .addTarget(content1)
                .addTarget(content2);

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(imageTransition).addTransition(cardTransition).addTransition(alphaTransition);

        TransitionManager.beginDelayedTransition(root, transitionSet);

        ViewGroup.LayoutParams params = cardView.getLayoutParams();
        params.height *= 2.5f;
        params.width *= 3f;
        cardView.setLayoutParams(params);

        ViewGroup.LayoutParams imageParams = imageView.getLayoutParams();
        imageParams.height *= 1.5f;
        imageParams.width *= 3f;
        imageView.setLayoutParams(imageParams);

        content1.setVisibility(View.VISIBLE);
        content2.setVisibility(View.VISIBLE);

        content1.setAlpha(1);
        content2.setAlpha(1);

    }

    private void collapseTransition() {
        Transition imageTransition = new ChangeImageTransform();
        imageTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(375);
        imageTransition.addTarget(imageView);

        Transition cardTransition = new ChangeBounds();
        cardTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(375).setStartDelay(75);
        cardTransition.addTarget(cardView).addTarget(imageView);

        Transition alphaTransition = new Fade();
        alphaTransition.setInterpolator(new FastOutLinearInInterpolator())
                .setDuration(75)
                .addTarget(content1)
                .addTarget(content2);

        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(cardTransition).addTransition(imageTransition).addTransition(alphaTransition);

        TransitionManager.beginDelayedTransition(root, transitionSet);

        ViewGroup.LayoutParams params = cardView.getLayoutParams();
        params.height /= 2.5f;
        params.width /= 3f;
        cardView.setLayoutParams(params);

        ViewGroup.LayoutParams imageParams = imageView.getLayoutParams();
        imageParams.height /= 1.5f;
        imageParams.width /= 3f;
        imageView.setLayoutParams(imageParams);

        content1.setVisibility(View.GONE);
        content2.setVisibility(View.GONE);
    }
}
