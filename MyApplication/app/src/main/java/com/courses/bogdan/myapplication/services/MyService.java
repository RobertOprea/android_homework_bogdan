package com.courses.bogdan.myapplication.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.LinearLayout;

import com.courses.bogdan.myapplication.constants.IntentActionConstants;

/**
 * Created by Bogdan on 11/9/2017.
 */

public class MyService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent();
        intent.setAction(IntentActionConstants.ACTIVITY_INFO_INTENT);
        intent.putExtra("fragmentActivity","text for fragment and activity");
        sendBroadcast(intent);
        intent.setAction(IntentActionConstants.FRAGMENT_INFO_INTENT);
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
