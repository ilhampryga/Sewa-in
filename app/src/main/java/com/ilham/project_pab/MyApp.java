// MyApp.java
package com.ilham.project_pab;

import android.app.Application;
import android.util.Log;

import com.cloudinary.android.MediaManager;
import java.util.HashMap;
import java.util.Map;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyApp", "onCreate: MediaManager init");
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dpik6fbdl");
        config.put("api_key", "932273116226425");
        MediaManager.init(this, config);
        }
    }
