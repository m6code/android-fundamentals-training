package com.m6code.materialmeroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

public class SportsRepository {

    private SportDao mSportDao;
    private LiveData<List<Sport>> mAllSports;

    public SportsRepository(Application app) {
        SportsRoomDatabase db = SportsRoomDatabase.getInstance(app);
        mSportDao = db.sportDao();
        mAllSports = mSportDao.getAllSports();
    }

    public LiveData<List<Sport>> getmAllSports() {
        return mAllSports;
    }

    public void insert(Sport sport) {
        new insertAsyncTask(mSportDao).execute(sport);
    }

    public void delete(Sport sport){
        new deleteAsyncTask(mSportDao).execute(sport);
    }

    public static class insertAsyncTask extends AsyncTask<Sport, Void, Void> {

        private SportDao mAsyncTaskDao;

        insertAsyncTask(SportDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            mAsyncTaskDao.insert(sports[0]);
            return null;
        }
    }

    public static class deleteAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao mAsyncSportDao;

        deleteAsyncTask(SportDao dao) {
            mAsyncSportDao = dao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            mAsyncSportDao.delete(sports[0]);
            return null;
        }
    }
}
