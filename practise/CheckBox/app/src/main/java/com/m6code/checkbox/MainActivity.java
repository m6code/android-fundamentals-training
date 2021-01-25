package com.m6code.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CheckBox chocolate;
    CheckBox sprinkles;
    CheckBox crushedNuts;
    CheckBox cherries;
    CheckBox orioCookies;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chocolate = findViewById(R.id.chocolate);
        sprinkles = findViewById(R.id.sprinkles);
        crushedNuts = findViewById(R.id.crushed_nuts);
        cherries = findViewById(R.id.cherries);
        orioCookies = findViewById(R.id.orio_cookie);
        mButton = findViewById(R.id.button);

        mButton.setOnClickListener(v -> showToast());

    }

    void showToast(){
        String msg = "Toppings: ";
        if (chocolate.isChecked()) msg = msg + " " + chocolate.getText().toString();
        if (sprinkles.isChecked()) msg = msg + " " + sprinkles.getText().toString();
        if (crushedNuts.isChecked()) msg = msg + " " + crushedNuts.getText().toString();
        if (cherries.isChecked()) msg = msg + " " + cherries.getText().toString();
        if (orioCookies.isChecked()) msg = msg + " " + orioCookies.getText().toString();

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}