package com.example.jiofiberapp;

import android.app.Application;
import android.os.StrictMode;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // For open csv
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
    }
}
