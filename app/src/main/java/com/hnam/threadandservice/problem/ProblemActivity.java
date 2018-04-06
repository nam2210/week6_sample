package com.hnam.threadandservice.problem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hnam.threadandservice.R;

/**
 * Created by nampham on 4/6/18.
 */
public class ProblemActivity extends AppCompatActivity {

    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        tvTitle = findViewById(R.id.textView);
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runCounter();
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProblemActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void runCounter(){
        int i = 0;
        tvTitle.setText("Start");
        while (i < 5){
            try {
                Thread.sleep(3000);
                i++;
                tvTitle.setText(String.valueOf(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tvTitle.setText("Done");
    }
}
