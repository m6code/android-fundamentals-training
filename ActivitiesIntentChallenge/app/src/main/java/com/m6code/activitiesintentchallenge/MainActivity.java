package com.m6code.activitiesintentchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static final String BTONE = "com.m6code.activitiesintentchallenge.extras.BTONE";
    public static final String BTWO = "com.m6code.activitiesintentchallenge.extras.BTWO";
    public static final String BTHREE = "com.m6code.activitiesintentchallenge.extras.BTHREE";
    public static final String PASSAGE = "com.m6code.activitiesintentchallenge.extras.PASSAGE";
    public static final int REQUEST_CODE = 1;
    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showPassageOne(View view) {
        launchPassage(BTONE);
    }

    public void showPassageTwo(View view) {
        launchPassage(BTWO);
    }

    public void showPassageThree(View view) {
        launchPassage(BTHREE);
    }

    public void launchPassage(String passageNo) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(PASSAGE, passageNo);
        startActivity(intent);
    }
}