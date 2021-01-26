package com.m6code.pickerfortime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            DialogFragment frag = new TimePickerFragment();
            frag.show(getSupportFragmentManager(), getString(R.string.timepicker));
        });

    }

    public void processTimeSet(int hour, int minute){
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);

        Toast.makeText(this,
                "Selected time is: " + hour_string + ":" + minute_string,
                Toast.LENGTH_SHORT).show();
    }
}