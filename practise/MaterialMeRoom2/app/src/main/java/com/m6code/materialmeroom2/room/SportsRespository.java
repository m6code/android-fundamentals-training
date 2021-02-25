package com.m6code.materialmeroom2.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.m6code.materialmeroom2.model.Sport;
import com.m6code.materialmeroom2.model.SportDao;

import java.util.List;

public class SportsRespository {
    private SportDao mSportDao;
    private LiveData<List<Sport>> allSports;

    public SportsRespository(Application application) {
        SportsRoomDatabase db = SportsRoomDatabase.getInstance(application);
        mSportDao = db.sportDao();
        allSports = mSportDao.getAllSports();
    }

    public LiveData<List<Sport>> getAllSports() {
        return allSports;
    }

    public void insert(Sport sport) {
        new insertAsyncTask(mSportDao).execute(sport);
    }

    public void delete(Sport sport) {
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
        private SportDao mAsyncDao;

        deleteAsyncTask(SportDao dao) {
            mAsyncDao = dao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            mAsyncDao.delete(sports[0]);
            return null;
        }
    }
}
