package com.m6code.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> mTextView;

    SimpleAsyncTask(TextView tv){
        mTextView = new WeakReference<>(tv);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int n = random.nextInt();
        int s = n * 200;

        try {
            Thread.sleep(s);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds";

    }

    @Override
    protected void onPostExecute(String s) {
        // Because mTextView is a weak reference, you have to deference it with the get() method to get the underlying TextView object, and to call setText() on it.
        mTextView.get().setText(s);
    }
}
