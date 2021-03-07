package com.m6code.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv) {
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int n = random.nextInt(11);
        int s = n * 200;

        for (int i = 0; i < s; i++) {
            if (isCancelled()) break;
            else {
                publishProgress(i);
            }
        }

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds";

    }

    @Override
    protected void onPostExecute(String s) {
        // Because mTextView is a weak reference, you have to deference it with the get()
        // method to get the underlying TextView object, and to call setText() on it.
        mTextView.get().setText(s);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        mTextView.get().setText("Napping - Sleep Time Counting.. " + values[0]);
    }
}
