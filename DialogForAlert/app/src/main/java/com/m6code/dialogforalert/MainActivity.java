package com.m6code.dialogforalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickShowAlert(View view) {
        AlertDialog.Builder myAlertBuilder = new AlertDialog.Builder(MainActivity.this);
        // Set the dialog title and message
        myAlertBuilder.setTitle("Alert");
        myAlertBuilder.setMessage("Click OK to continue, or Cancel to stop:");
        // Add the dialog buttons
        myAlertBuilder.setPositiveButton("OK",
                (dialog, which) -> Toast.makeText(MainActivity.this, "Pressed Ok", Toast.LENGTH_SHORT).show());
        myAlertBuilder.setNegativeButton("Cancel",
                (dialog, which) -> Toast.makeText(this, "Pressed Cancel", Toast.LENGTH_SHORT).show());
        // Create and show the alertDialog.
        myAlertBuilder.show();
    }
}