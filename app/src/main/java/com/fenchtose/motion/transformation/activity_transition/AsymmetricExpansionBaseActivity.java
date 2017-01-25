package com.fenchtose.motion.transformation.activity_transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import com.fenchtose.motion.R;
import com.fenchtose.motion.transitions.AsymmetricCollapse;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AsymmetricExpansionBaseActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.root) ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setReenterTransition(new AsymmetricCollapse(110, 330, 110, 330).addTarget(R.id.cardview));
        setContentView(R.layout.activity_asymmetric_expansion_base);
        ButterKnife.bind(this);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AsymmetricExpansionBaseActivity.this, AsymmetricExpansionReceiverActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AsymmetricExpansionBaseActivity.this, root, "root");
                startActivity(intent, options.toBundle());
            }
        });
    }
}
