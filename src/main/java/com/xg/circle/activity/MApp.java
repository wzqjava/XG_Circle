package com.xg.circle.activity;

import android.app.Application;

import com.xg.circle.utils.ImageLoaderFactory;

/**
 * Created by xugang on 2016/4/11.
 * Describe
 */
public class MApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderFactory.init(getApplicationContext());
    }
}
