package com.m6code.saverestorestatechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    TextView one;
    TextView two;
    TextView three;
    TextView four;
    TextView five;
    TextView six;
    TextView seven;
    TextView eight;
    TextView nine;
    TextView ten;
    FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one = findViewById(R.id.tv_one);
        two = findViewById(R.id.tv_two);
        three = findViewById(R.id.tv_three);
        four = findViewById(R.id.tv_four);
        five = findViewById(R.id.tv_five);
        six = findViewById(R.id.tv_six);
        seven = findViewById(R.id.tv_seven);
        eight = findViewById(R.id.tv_eight);
        nine = findViewById(R.id.tv_nine);
        ten = findViewById(R.id.tv_ten);
        mFloatingActionButton = findViewById(R.id.floatingActionButton);

        Intent replyIntent = getIntent();
        Log.d(TAG, "onCreate: replyIntent is - " + replyIntent.getStringExtra(AddToListActivity.ADD_ITEM));

        if (savedInstanceState != null) {
            one.setText(savedInstanceState.getString("one"));
            two.setText(savedInstanceState.getString("two"));
            three.setText(savedInstanceState.getString("three"));
            four.setText(savedInstanceState.getString("four"));
            five.setText(savedInstanceState.getString("five"));
            six.setText(savedInstanceState.getString("six"));
            seven.setText(savedInstanceState.getString("seven"));
            eight.setText(savedInstanceState.getString("eight"));
            nine.setText(savedInstanceState.getString("nine"));
            ten.setText(savedInstanceState.getString("ten"));
        }

        mFloatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddToListActivity.class);
            startActivityForResult(intent, TEXT_REQUEST);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(AddToListActivity.ADD_ITEM);
                if (reply != null) {
                    if (one.getText().toString().isEmpty()) one.setText(reply);
                    else if (two.getText().toString().isEmpty()) two.setText(reply);
                    else if (three.getText().toString().isEmpty()) three.setText(reply);
                    else if (four.getText().toString().isEmpty()) four.setText(reply);
                    else if (five.getText().toString().isEmpty()) five.setText(reply);
                    else if (six.getText().toString().isEmpty()) six.setText(reply);
                    else if (seven.getText().toString().isEmpty()) seven.setText(reply);
                    else if (eight.getText().toString().isEmpty()) eight.setText(reply);
                    else if (nine.getText().toString().isEmpty()) nine.setText(reply);
                    else if (ten.getText().toString().isEmpty()) ten.setText(reply);
                    else Toast.makeText(MainActivity.this, "List is Already Filled Up",
                                Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!one.getText().toString().isEmpty())
            outState.putString("one", one.getText().toString());
        if (!two.getText().toString().isEmpty())
            outState.putString("two", two.getText().toString());
        if (!three.getText().toString().isEmpty())
            outState.putString("three", three.getText().toString());
        if (!four.getText().toString().isEmpty())
            outState.putString("four", four.getText().toString());
        if (!five.getText().toString().isEmpty())
            outState.putString("five", five.getText().toString());
        if (!six.getText().toString().isEmpty())
            outState.putString("six", six.getText().toString());
        if (!seven.getText().toString().isEmpty())
            outState.putString("seven", seven.getText().toString());
        if (!eight.getText().toString().isEmpty())
            outState.putString("eight", eight.getText().toString());
        if (!nine.getText().toString().isEmpty())
            outState.putString("nine", nine.getText().toString());
        if (!ten.getText().toString().isEmpty())
            outState.putString("ten", ten.getText().toString());
    }
}