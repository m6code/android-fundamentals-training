package com.m6code.droidcafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        String message = "Order: "+
                intent.getStringExtra(MainActivity.EXTRA_MSG);
        TextView textView = findViewById(R.id.order_textView);
        textView.setText(message);
    }
}