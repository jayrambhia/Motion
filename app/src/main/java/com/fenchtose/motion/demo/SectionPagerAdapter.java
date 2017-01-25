package com.fenchtose.motion.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class SectionPagerAdapter extends PagerAdapter {

    private List<Section> sections;
    private Context context;

    private DemoCallback callback;

    public SectionPagerAdapter(Context context) {
        this.context = context;
        sections = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sections.get(position).getName();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RecyclerView recyclerView = initializeRecyclerView(position);
        container.addView(recyclerView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return recyclerView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        RecyclerView recyclerView = (RecyclerView)object;
        container.removeView(recyclerView);
    }

    private RecyclerView initializeRecyclerView(final int position) {
        RecyclerView recyclerView = new RecyclerView(context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        DemoAdapter adapter = new DemoAdapter(context);
        adapter.setHasStableIds(true);
        adapter.setData(sections.get(position).getDemos());

        adapter.setClickListener(new DemoAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(int item) {
                if (callback != null) {
                    callback.openDemo(sections.get(position).getDemos().get(item).getActivity());
                }
            }
        });

        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

    public void addSection(@NonNull Section section) {
        sections.add(section);
    }

    public void addSections(@NonNull List<Section> sections) {
        this.sections.addAll(sections);
    }

    public void setCallback(DemoCallback callback) {
        this.callback = callback;
    }

    public interface DemoCallback {
        void openDemo(@NonNull Class<?> clazz);
    }
}
