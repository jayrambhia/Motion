package com.fenchtose.motion.demo;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fenchtose.motion.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private List<Section.Demo> demos;
    private OnItemClickListener clickListener;

    public DemoAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.demo_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder)holder;
        viewHolder.textView.setText(demos.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return demos == null ? 0 : demos.size();
    }

    @Override
    public long getItemId(int position) {
        return demos.get(position).getTitle().hashCode();
    }

    public void setData(@Nullable List<Section.Demo> demos) {
        this.demos = demos;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview) TextView textView;

        private Unbinder unbinder;

        public ItemViewHolder(View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onItemClicked(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
