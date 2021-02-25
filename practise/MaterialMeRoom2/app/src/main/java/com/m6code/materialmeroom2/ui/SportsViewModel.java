package com.m6code.materialmeroom2.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.m6code.materialmeroom2.model.Sport;
import com.m6code.materialmeroom2.room.SportsRespository;

import java.util.List;

public class SportsViewModel extends AndroidViewModel {
    private SportsRespository mRespository;
    private LiveData<List<Sport>> allSports;

    public SportsViewModel(@NonNull Application application) {
        super(application);
        mRespository = new SportsRespository(application);
        allSports = mRespository.getAllSports();
    }

    public void insert(Sport sport) {
        mRespository.insert(sport);
    }

    public void delete(Sport sport) {
        mRespository.delete(sport);
    }

    public LiveData<List<Sport>> getAllSports() {
        return allSports;
    }
}
