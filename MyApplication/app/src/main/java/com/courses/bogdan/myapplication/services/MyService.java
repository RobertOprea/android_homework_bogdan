package com.courses.bogdan.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;

/**
 * Created by Bogdan on 11/9/2017.
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("TAGG","Service created");
        Intent intent = new Intent();
        intent.setAction("com.courses.EXAMPLE");
        sendBroadcast(intent);
        stopSelf();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG destroy", "BUM Service destroyed");
    }
}
