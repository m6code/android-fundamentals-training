package com.m6code.materialmeroom;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface SportDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sport sport);

    @Query("SELECT * FROM sports_table ORDER BY id ASC")
    LiveData<List<Sport>> getAllSports();

    @Delete
    void delete(Sport sport);

    @Query("SELECT * FROM sports_table LIMIT 1")
    Sport[] getAnySport();

//    @Query("UPDATE sports_table SET title = :title WHERE id = :id")
//    void updateNoteTitle(int id, String title);
//
//    @Query("UPDATE sports_table SET info = :info WHERE id = :id ")
//    void updateNoteBody(int id, String info);
}
