package com.fenchtose.motion.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fenchtose.motion.R;
import com.fenchtose.motion.choreography.shared_expand.SharedElementExpansionActivity;
import com.fenchtose.motion.movement.BasicArcMotionActivity;
import com.fenchtose.motion.movement.BasicLinearMotionActivity;
import com.fenchtose.motion.movement.BasicScreenEnterMotionActivity;
import com.fenchtose.motion.movement.BasicScreenEnterRelativeMotionActivity;
import com.fenchtose.motion.movement.BasicScreenTemporaryExitMovementActivity;
import com.fenchtose.motion.transformation.BasicAsymmetricTransformationActivity;
import com.fenchtose.motion.transformation.BasicSymmetricTransformationActivity;
import com.fenchtose.motion.transformation.FullBleedAsymmetricTransitionActivity;
import com.fenchtose.motion.transformation.FullBleedSymmetricExpansionActivity;
import com.fenchtose.motion.transformation.RadialArcTransformationActivity;
import com.fenchtose.motion.transformation.RadialFixedTransformationActivity;
import com.fenchtose.motion.transformation.RadialLinearTransformationActivity;
import com.fenchtose.motion.transformation.activity_transition.AsymmetricExpansionBaseActivity;
import com.fenchtose.motion.transformation.activity_transition.SymmetricImageExpansionBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DemoListActivity extends AppCompatActivity {

    @BindView(R.id.view_pager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_list);
        ButterKnife.bind(this);
        setupData();
    }

    private void setupData() {
        SectionPagerAdapter adapter = new SectionPagerAdapter(this);

        Section movement = new Section("Movement");
        movement.addDemo("Arc Motion", BasicArcMotionActivity.class);
        movement.addDemo("Linear Motion", BasicLinearMotionActivity.class);
        movement.addDemo("Screen Enter Motion", BasicScreenEnterMotionActivity.class);
        movement.addDemo("Screen Temporary Exit", BasicScreenTemporaryExitMovementActivity.class);
        movement.addDemo("Screen Enter Relative", BasicScreenEnterRelativeMotionActivity.class);

        Section transformation = new Section("Transformation");
        transformation.addDemo("Asymmetric Transformation", BasicAsymmetricTransformationActivity.class);
        transformation.addDemo("Symmetric Transformation", BasicSymmetricTransformationActivity.class);
        transformation.addDemo("Full Bleed Symmetric", FullBleedSymmetricExpansionActivity.class);
        transformation.addDemo("Full Bleed Asymmetric", FullBleedAsymmetricTransitionActivity.class);
        transformation.addDemo("Radial Arc Transformation", RadialArcTransformationActivity.class);
        transformation.addDemo("Radial Linear Transformation", RadialLinearTransformationActivity.class);
        transformation.addDemo("Radial Fixed Transformation", RadialFixedTransformationActivity.class);

        Section choreography = new Section("Choreography");
        choreography.addDemo("Share All Content", SharedElementExpansionActivity.class);

        Section activityTransitions = new Section("Activity Transitions");
        activityTransitions.addDemo("Asymmetric Expansion", AsymmetricExpansionBaseActivity.class);
        activityTransitions.addDemo("Symmetric Expansion", SymmetricImageExpansionBaseActivity.class);

        adapter.addSection(activityTransitions);
        adapter.addSection(movement);
        adapter.addSection(transformation);
        adapter.addSection(choreography);

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        adapter.setCallback(new SectionPagerAdapter.DemoCallback() {
            @Override
            public void openDemo(@NonNull Class<?> clazz) {
                openActivity(clazz);
            }
        });
    }

    private void openActivity(@NonNull Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
