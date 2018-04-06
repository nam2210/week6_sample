package com.hnam.threadandservice;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hnam.threadandservice.asynctask.AsyncTaskActivity;
import com.hnam.threadandservice.problem.ProblemActivity;
import com.hnam.threadandservice.services.ServiceActivity;
import com.hnam.threadandservice.thread.ThreadActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_asynctask).setOnClickListener(onButtonClickListener);
        findViewById(R.id.btn_service).setOnClickListener(onButtonClickListener);
        findViewById(R.id.btn_thread).setOnClickListener(onButtonClickListener);
        findViewById(R.id.btn_problems).setOnClickListener(onButtonClickListener);
    }

    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_problems: {
                    Intent i = new Intent(MainActivity.this, ProblemActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.btn_asynctask: {
                    Intent i = new Intent(MainActivity.this, AsyncTaskActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.btn_thread: {
                    Intent i = new Intent(MainActivity.this, ThreadActivity.class);
                    startActivity(i);
                    break;
                }
                case R.id.btn_service: {
                    Intent i = new Intent(MainActivity.this, ServiceActivity.class);
                    startActivity(i);
                    break;
                }
            }
        }
    };
}
