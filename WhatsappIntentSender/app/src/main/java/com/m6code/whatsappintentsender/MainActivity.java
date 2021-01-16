package com.m6code.whatsappintentsender;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send = findViewById(R.id.button_send_message);
        EditText phone = findViewById(R.id.EditText_phone);
        EditText message = findViewById(R.id.EditText_message);

        send.setOnClickListener(v -> {
            // get phone
            String phoneNum = phone.getText().toString();
            // get message
            String messageContent = message.getText().toString();

            // Send Message using implicit whatsapp intent
            Uri wapp = Uri.parse("https://api.whatsapp.com/send/" +
                    "?phone=" + phoneNum
                    + "&text=" + messageContent);
            Intent intent = new Intent(Intent.ACTION_VIEW, wapp);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, "No App to handle intent", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }
}