package com.hnam.threadandservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

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

        new MyAsyncTask().execute();
    }

    private class MyAsyncTask extends AsyncTask<Void, Integer, Boolean>{

        @Override
        protected Boolean doInBackground(Void... voids) {
            int i = 0;
            while (i < 10){
                try {
                    i++;
                    publishProgress(i);
                    Thread.sleep(1000);
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
