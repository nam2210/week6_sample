package com.hnam.threadandservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_asynctask).setOnClickListener(onButtonClickListener);
        findViewById(R.id.btn_service).setOnClickListener(onButtonClickListener);
        findViewById(R.id.btn_thread).setOnClickListener(onButtonClickListener);
    }

    private View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_asynctask:
                    //todo
                    break;
                case R.id.btn_thread:
                    //todo
                    break;
                case R.id.btn_service:
                    //todo
                    break;
            }
        }
    };
}
