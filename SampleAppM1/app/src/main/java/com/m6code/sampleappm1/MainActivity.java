package com.m6code.sampleappm1;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomAppbar);

        bottomAppBar.setNavigationOnClickListener(v -> {
            showToast("Navigation item clicked");
        });

        bottomAppBar.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.search) {
                // Handle search
                showToast("Menu item clicked - Search");
                return true;
            } else if (menuItem.getItemId() == R.id.menu_more) {
                showToast("Menu item clicked - Some other item");
                return true;
            } else if (menuItem.getItemId() == R.id.menu_more2) {
                showToast("Menu item clicked - Third item");
                return true;
            } else if (menuItem.getItemId() == R.id.more) {
                showToast("Menu item clicked - More");
                return true;
            } else return false;
        });
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}