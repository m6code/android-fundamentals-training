package com.m6code.standup;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        alarmToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String toastMsg;
            if (isChecked){
                // Set the toast message for the "on" case.
                toastMsg = getString(R.string.stand_up_on);
            }else {
                // Set the toast message for the "off" case.
                toastMsg = getString(R.string.stand_up_off);
            }

            // Show a toast to say the alarm is turned on or off.
            Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
        });
    }
}