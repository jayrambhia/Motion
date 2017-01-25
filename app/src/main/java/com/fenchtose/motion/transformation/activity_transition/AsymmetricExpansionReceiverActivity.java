package com.fenchtose.motion.transformation.activity_transition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fenchtose.motion.R;
import com.fenchtose.motion.transitions.AsymmetricCollapse;
import com.fenchtose.motion.transitions.AsymmetricExpansion;

public class AsymmetricExpansionReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setEnterTransition(new AsymmetricExpansion(110, 330, 110, 330, 50).addTarget(R.id.cardview));
        getWindow().setReturnTransition(new AsymmetricCollapse(330, 110, 330, 110, 50).addTarget(R.id.cardview));
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asymmetric_expansion_receiver);
    }
}
