package com.fenchtose.motion.choreography.shared_expand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fenchtose.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SharedElementExpansionActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_expansion);
        ButterKnife.bind(this);

        ExpandableAdapter adapter = new ExpandableAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
