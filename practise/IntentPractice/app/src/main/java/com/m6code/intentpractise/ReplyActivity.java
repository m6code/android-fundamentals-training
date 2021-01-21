package com.m6code.intentpractise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReplyActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.m6code.intentpractise.extra.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        TextView messageTV = findViewById(R.id.textView_received);
        Button ok = findViewById(R.id.button_okay);
        Button fine = findViewById(R.id.button_fine);
        Button yes = findViewById(R.id.button_yes);
        Button go = findViewById(R.id.button_go);
        Button shout = findViewById(R.id.button_shout);
        Button come = findViewById(R.id.button_come);
        Button man = findViewById(R.id.button_man);

        Intent intent = getIntent();

        String num = intent.getStringExtra(MainActivity.EXTRA_NUM);
        String msg = intent.getStringExtra(MainActivity.EXTRA_MSG);
        messageTV.setText(String.format("Number: %s %nMessage: %s", num, msg));

        ok.setOnClickListener(v -> sendReply(ok.getText().toString()));
        fine.setOnClickListener(v -> sendReply(fine.getText().toString()));
        yes.setOnClickListener(v -> sendReply(yes.getText().toString()));
        go.setOnClickListener(v -> sendReply(go.getText().toString()));
        shout.setOnClickListener(v -> sendReply(shout.getText().toString()));
        come.setOnClickListener(v -> sendReply(come.getText().toString()));
        man.setOnClickListener(v -> sendReply(man.getText().toString()));
    }

    private void sendReply(String text) {
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, text);
        setResult(RESULT_OK, replyIntent);
        finish();
    }
}