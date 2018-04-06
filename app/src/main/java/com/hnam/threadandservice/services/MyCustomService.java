package com.hnam.threadandservice.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by nampham on 4/6/18.
 */
public class MyCustomService extends Service{
    private static final String TAG = MyCustomService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"start service");
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mLocalBroadcastManager.registerReceiver(receiver, getIntentFiler());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(receiver);
    }

    private IntentFilter getIntentFiler(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceActivity.START_CMD);
        return filter;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e(TAG, "action: " + action);
            if (action.equalsIgnoreCase(ServiceActivity.START_CMD)){
                //start thread handler
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runCounter();
                    }
                }).start();
            }
        }
    };


    private void runCounter(){
        int i = 0;

        sendMessages(ServiceActivity.VALUE_CMD, "start");
        while (i < 5){
            try {
                Thread.sleep(3000);
                i++;
                sendMessages(ServiceActivity.VALUE_CMD, String.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sendMessages(ServiceActivity.VALUE_CMD, "Done!!!");
    }

    private void sendMessages(String action, String data){
       Intent intent = new Intent(action);
       intent.putExtra("data", data);
       mLocalBroadcastManager.sendBroadcast(intent);
    }

}
