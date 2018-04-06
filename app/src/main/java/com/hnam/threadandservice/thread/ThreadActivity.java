package com.hnam.threadandservice.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hnam.threadandservice.R;
import com.hnam.threadandservice.problem.ProblemActivity;

import java.lang.ref.WeakReference;

/**
 * Created by nampham on 4/6/18.
 */
public class ThreadActivity extends AppCompatActivity {

    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tvTitle = findViewById(R.id.textView);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startCounter1();
                startCounter2();
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThreadActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void runCounter(){
        int i = 0;

        sendMessages("Start");
        while (i < 5){
            try {
                Thread.sleep(3000);
                i++;
                sendMessages(String.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        sendMessages(String.valueOf("Done!!!"));
    }

    private void sendMessages(String data){
        Message msg = new Message();
        Bundle b1 = new Bundle();
        b1.putString("Data", data);
        msg.setData(b1);
        msg.what = 1;
        handler.sendMessage(msg);
    }

    //handler
    private final Handler handler = new MyHandler(this);
    private static class MyHandler extends Handler{
        private final WeakReference<ThreadActivity> reference;

        public MyHandler(ThreadActivity activity){
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThreadActivity activity = reference.get();
            switch (msg.what){
                case 1:
                    Bundle b = msg.getData();
                    String data = b.getString("Data");
                    activity.tvTitle.setText(data);
                    break;
            }
        }
    }

    // =======================
    // su dung thread runnbale
    //========================
    private void startCounter1(){
        new Thread(runnable).start();
    }


    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            runCounter();
        }
    };


    // =======================
    // su dung thread
    // =======================
    private void startCounter2(){
        new MyThread().start();
    }

    private class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            runCounter();
        }
    }




    
}
