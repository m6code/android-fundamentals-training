package com.m6code.activitiesintentchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    public static final String REPLY = "com.m6code.activitiesintentchallenge.extras.REPLY";
    private static final String TAG = SecondActivity.class.getSimpleName();
    private TextView mMessageReceived;
    private EditText mEditTextReply;
    private Button mButtonReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mMessageReceived = findViewById(R.id.textView_message);
        mEditTextReply = findViewById(R.id.editText_reply_msg);
        mButtonReply = findViewById(R.id.button_reply);


        Intent intent = getIntent();
        String msg = intent.getStringExtra(MainActivity.MESSAGE);
        mMessageReceived.setText(msg);
    }

    public void sendReply(View view) {
        String reply = mEditTextReply.getText().toString();
        Intent reIntent = new Intent();
        reIntent.putExtra(REPLY, reply);
        setResult(RESULT_OK, reIntent);
        finish();
    }
}