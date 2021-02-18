package com.m6code.materialmeroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Sport.class}, version = 1, exportSchema = false)
public abstract class SportsRoomDatabase extends RoomDatabase {

    public abstract SportDao sportDao();

    private static SportsRoomDatabase INSTANCE;

    static SportsRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    SportsRoomDatabase.class, "materialme_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCallBack)
                    .build();
        }

        return INSTANCE;
    }

    public static RoomDatabase.Callback sRoomDatabaseCallBack =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final SportDao mDao;

        String[] sportsTitle = {"Baseball", "Badminton", "Basketball", "Bowling", "Cycling"};
        //String[] sportsInfo = {"Here is some Baseball news!", "Here is some Badminton news!", "", "", "", ""};

        String[] sportsImg = {"baseball", "badminton", "basketball", "bowling", "cycling"};

        PopulateDbAsync(SportsRoomDatabase db){
            mDao = db.sportDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            if we have no sports, then create the initial list of sports
            if(mDao.getAnySport().length < 1){
                for (int i = 0; i <= sportsTitle.length -1; i++){
                    Sport sport = new Sport(sportsTitle[i],
                            "Here is some " + sportsTitle[i] + " news!",
                            "@drawable/img_"+sportsImg[i]);
                    mDao.insert(sport);
                }
            }
            return null;
        }
    }
}
