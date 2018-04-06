package com.hnam.threadandservice.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hnam.threadandservice.R;
import com.hnam.threadandservice.problem.ProblemActivity;

/**
 * Created by nampham on 4/6/18.
 */
public class AsyncTaskActivity extends AppCompatActivity {

    private TextView tvTitle;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        tvTitle = findViewById(R.id.textView);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvTitle.setText("Start");
                new MyAsyncTask().execute();
            }
        });


        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AsyncTaskActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            int i = 0;
            while (i < 5){
                try {
                    Thread.sleep(3000);
                    //mô phỏng thời gian xử lý mất hết 1s
                    i++;
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvTitle.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            tvTitle.setText("Done!!!");
        }


    }
}
