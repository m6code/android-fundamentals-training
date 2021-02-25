package com.m6code.materialmeroom2.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.m6code.materialmeroom2.model.Sport;
import com.m6code.materialmeroom2.model.SportDao;

public abstract class SportsRoomDatabase extends RoomDatabase {

    public abstract SportDao sportDao();

    private static SportsRoomDatabase INSTANCE;

    //Get an instance of the room database
    // Create one if it doesn't exist
    static  SportsRoomDatabase getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SportsRoomDatabase.class, "materialme_roomdatabase")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallBack)
                    .build();
        }
        return INSTANCE;
    }


    // Populate the database with initial sports data on creation
    public static RoomDatabase.Callback sRoomDatabaseCallBack =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDBAsync(INSTANCE).execute();
                }
            };


    public static class PopulateDBAsync extends AsyncTask<Void, Void, Void>{
        private final SportDao mDao;

        String[] sportsTitle = {
                "Baseball", "Badminton", "Basketball", "Bowling", "Cycling",
                "Golf", "Running", "Soccer", "Swimming", "Table Tennis", "Tennis"
        };

        PopulateDBAsync(SportsRoomDatabase db){
            mDao = db.sportDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (mDao.getAnySport().length < 1){
                for (int i = 0; i <= sportsTitle.length -1; i++){
                    Sport sport = new Sport(sportsTitle[i],
                            "Here is some "+ sportsTitle[i] + " news!",
                            "img_"+ sportsTitle[i].replace("\\s+", "").toLowerCase());
                }
            }
            return null;
        }
    }
}
