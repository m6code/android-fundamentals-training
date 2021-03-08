package com.m6code.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private final WeakReference<TextView> mTextView;
    private final WeakReference<ProgressBar> mProgress;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgress = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random random = new Random();
        int n = random.nextInt(11);
        int s = n * 20000;

        for (int i = 0; i < s; i++) {
            if (isCancelled()) break;
            else {
                // TODO: Convert value to percentage before publishing progress;
                int per = (int) (i * 100 / s + 0.5);
                publishProgress(per);
            }
        }

        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return String.valueOf(s);

    }

    @Override
    protected void onPostExecute(String s) {
        // Because mTextView is a weak reference, you have to deference it with the get()
        // method to get the underlying TextView object, and to call setText() on it.
        mTextView.get().setText("Awake at last after sleeping for " + s + " milliseconds");
        mProgress.get().setProgress(Integer.parseInt(s));
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

//        if (mTextView.get() != null) {
            mTextView.get().setText("Napping - Sleep Time Counting.. " + values[0] + "%");
            mProgress.get().setProgress(values[0]);
//        }
    }
}
