package com.fenchtose.motion.transformation.activity_transition;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;

import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SymmetricImageExpansionBaseActivity extends AppCompatActivity {

    @BindView(R.id.cardview) CardView cardView;
    @BindView(R.id.root) ViewGroup root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symmetric_image_expansion_base);

        ButterKnife.bind(this);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SymmetricImageExpansionBaseActivity.this, SymmetricImageExpansionReceiverActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SymmetricImageExpansionBaseActivity.this, root, "root");
                startActivity(intent, options.toBundle());
            }
        });
    }
}
