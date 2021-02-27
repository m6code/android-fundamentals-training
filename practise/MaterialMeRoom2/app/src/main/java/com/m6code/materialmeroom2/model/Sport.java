package com.m6code.materialmeroom2.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sports_table")
public class Sport {

    @NonNull
    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo
    @NonNull
    private String title;

    @ColumnInfo
    @NonNull
    private String info;

    @ColumnInfo(name = "image_resource_id")
    @NonNull
    private final String imageResource;

    public Sport(String title,String info, String imageResource) {
        this.info = info;
        this.title = title;
        this.imageResource = imageResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    public String getImageResource() {
        return imageResource;
    }

    @NonNull
    public String getInfo() {
        return info;
    }

    public void setInfo(@NonNull String info) {
        this.info = info;
    }
}
