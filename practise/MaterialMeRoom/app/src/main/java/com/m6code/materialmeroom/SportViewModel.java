package com.m6code.materialmeroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SportViewModel extends AndroidViewModel {

    private SportsRepository mRepository;
    private LiveData<List<Sport>> mAllSports;

    public SportViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SportsRepository(application);
        mAllSports = mRepository.getmAllSports();
    }

    public void insert(Sport sport){
        mRepository.insert(sport);
    }

    public void delete(Sport sport){
        mRepository.delete(sport);
    }

    public LiveData<List<Sport>> getmAllSports() {
        return mAllSports;
    }
}
