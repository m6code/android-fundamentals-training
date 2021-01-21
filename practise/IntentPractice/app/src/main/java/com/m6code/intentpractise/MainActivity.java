package com.m6code.intentpractise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_NUM = "com.m6code.intentpractise.extra.NUMBER";
    public static final String EXTRA_MSG = "com.m6code.intentpractise.extra.MESSAGE";
    public final static int REQUEST_CODE = 1;
    private TextView mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText number = findViewById(R.id.number);
        TextInputEditText message = findViewById(R.id.message);
        Button send = findViewById(R.id.button_send);
        mReply = findViewById(R.id.textView_Reply_text);

        send.setOnClickListener(v -> {
            if (message.getText().toString().isEmpty() && number.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please enter a number or message", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, ReplyActivity.class);
                intent.putExtra(EXTRA_NUM, number.getText().toString());
                intent.putExtra(EXTRA_MSG, message.getText().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String reply = data.getStringExtra(ReplyActivity.EXTRA_REPLY);

            if (reply != null) mReply.setText(data.getStringExtra(ReplyActivity.EXTRA_REPLY));
        }
    }
}