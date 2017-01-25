package com.fenchtose.motion.choreography.shared_expand;

import android.content.Context;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fenchtose.motion.R;
import com.fenchtose.motion.utils.DimensionUtils;
import com.fenchtose.motion.utils.MotionAnimationUtils;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class ExpandableAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private float density;

    public ExpandableAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        density = context.getResources().getDisplayMetrics().density;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SharedExpandableViewHolder(inflater.inflate(R.layout.shared_expand_item_layout, parent, false), density);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class SharedExpandableViewHolder extends RecyclerView.ViewHolder {

        private int collapsedHeight;
        private int expandedHeight;

        private View root;
        private boolean isExpanded = false;

        @BindViews({R.id.content1, R.id.content2, R.id.content3})
        List<View> contents;

        public SharedExpandableViewHolder(View itemView, float density) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.root = itemView;
            collapsedHeight = DimensionUtils.dpToPx(80, density);
            expandedHeight = DimensionUtils.dpToPx(336, density);
            itemView.setOnClickListener(new View.OnClickListener() {
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
        }

        public void expand() {
            MotionAnimationUtils.expandSymmetric2(root, 1f, (float)(expandedHeight)/collapsedHeight, 375);
            for (View view : contents) {
                MotionAnimationUtils.animateAlpha(view, 0, 1, 150, 75, new FastOutSlowInInterpolator());
            }
        }

        public void collapse() {
            MotionAnimationUtils.expandSymmetric2(root, 1f, (float)(collapsedHeight)/expandedHeight, 375);
            for (View view : contents) {
                MotionAnimationUtils.animateAlpha(view, 1, 0, 75);
            }
        }
    }
}
