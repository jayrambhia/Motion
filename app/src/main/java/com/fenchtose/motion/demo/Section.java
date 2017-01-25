package com.fenchtose.motion.demo;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jay Rambhia on 9/9/16.
 */
public class Section {

    private String name;
    private List<Demo> demos;

    public Section(String name) {
        this.name = name;
        demos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Demo> getDemos() {
        return demos;
    }

    public void addDemo(@NonNull Demo demo) {
        demos.add(demo);
    }

    public void addDemo(@NonNull String title, @NonNull Class<?> clazz) {
        addDemo(new Demo(title, clazz));
    }

    public static class Demo {
        private String title;
        private Class<?> activity;

        public Demo(String title, Class<?> activity) {
            this.title = title;
            this.activity = activity;
        }

        public String getTitle() {
            return title;
        }

        public Class<?> getActivity() {
            return activity;
        }
    }
}
