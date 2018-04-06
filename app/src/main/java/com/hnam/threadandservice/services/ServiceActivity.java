package com.hnam.threadandservice.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hnam.threadandservice.R;
import com.hnam.threadandservice.thread.ThreadActivity;

/**
 * Created by nampham on 4/6/18.
 */
public class ServiceActivity extends AppCompatActivity {

    TextView tvTitle;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tvTitle = findViewById(R.id.textView);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(START_CMD);
                mLocalBroadcastManager.sendBroadcast(intent);
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ServiceActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //start service
        Intent i = new Intent(this, MyCustomService.class);
        startService(i);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mLocalBroadcastManager.registerReceiver(receiver, getIntentFiler());
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocalBroadcastManager.unregisterReceiver(receiver);
    }

    private static final String PACKAGE = "com.hnam.threadandservice";
    public static final String START_CMD = PACKAGE + ".START_CMD";
    public static final String VALUE_CMD = PACKAGE + ".VALUE_CMD";

    private IntentFilter getIntentFiler(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(VALUE_CMD);
        return filter;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
             if (action.equalsIgnoreCase(VALUE_CMD)){
                 String data = intent.getStringExtra("data");
                 tvTitle.setText(data);
            }
        }
    };
}
