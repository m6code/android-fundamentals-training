package com.m6code.activitiesintentchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    private TextView mTextViewPassage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mTextViewPassage = findViewById(R.id.textView_passage);

        Intent intent = getIntent();
        String passage = intent.getStringExtra(MainActivity.PASSAGE);

        Log.d(TAG, "onCreate: passage = " + passage);
        switch (passage) {
            case MainActivity.BTONE:
                mTextViewPassage.setText(R.string.passage_one);
                break;
            case MainActivity.BTWO:
                mTextViewPassage.setText(R.string.passage_two);
                break;
            case MainActivity.BTHREE:
                mTextViewPassage.setText(R.string.passage_three);
                break;
            default:
                mTextViewPassage.setText(R.string.error);
                break;
        }

    }

}