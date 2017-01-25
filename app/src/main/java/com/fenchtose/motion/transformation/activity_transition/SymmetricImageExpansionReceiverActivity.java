package com.fenchtose.motion.transformation.activity_transition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;

import com.fenchtose.motion.R;
import com.fenchtose.motion.transitions.AsymmetricCollapse;
import com.fenchtose.motion.transitions.AsymmetricCollapse2;
import com.fenchtose.motion.transitions.AsymmetricExpansion;
import com.fenchtose.motion.transitions.AsymmetricExpansion2;
import com.fenchtose.motion.utils.DimensionUtils;

public class SymmetricImageExpansionReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        float density = getResources().getDisplayMetrics().density;

        int startDim = DimensionUtils.dpToPx(112, density);
        int endWidth = DimensionUtils.dpToPx(336, density);
        int endHeight = DimensionUtils.dpToPx(224, density);

//        getWindow().setEnterTransition(new ChangeBounds().addTarget(R.id.cardview));

        getWindow().setEnterTransition(new AsymmetricExpansion2(startDim, endWidth, startDim, endHeight, 0).addTarget(R.id.cardview));
        getWindow().setReturnTransition(new AsymmetricCollapse2(endWidth, startDim, endHeight, startDim, 0).addTarget(R.id.cardview));

        setContentView(R.layout.activity_symmetric_image_expansion_receiver);
    }
}
