package com.m6code.materialmeroom2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.m6code.materialmeroom2.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        TextView sportTitle = findViewById(R.id.titleDetail);
        ImageView sportsImage = findViewById(R.id.sportsImageDetail);
        sportTitle.setText(intent.getStringExtra("title"));

        int imgId = intent.getIntExtra("image_resource_id", 0);
        if (imgId != 0) {
            Glide.with(this)
                    .load(intent.getIntExtra("image_resource_id", 0))
                    .into(sportsImage);
        }else {
            Glide.with(this)
                    .load(intent.getStringExtra("image_resource_string"))
                    .centerCrop()
                    .into(sportsImage);
        }
    }
}