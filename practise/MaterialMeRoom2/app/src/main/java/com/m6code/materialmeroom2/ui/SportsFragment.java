package com.m6code.materialmeroom2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.m6code.materialmeroom2.R;
import com.m6code.materialmeroom2.model.Sport;

import java.util.List;

public class SportsFragment extends Fragment {

    public static final String TAG = SportsFragment.class.getSimpleName();

    public SportsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SportsViewModel mSportsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_sport, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView);
        mSportsViewModel = new ViewModelProvider(this).get(SportsViewModel.class);

        // Get gridColumnCount
        int gridCount = getResources().getInteger(R.integer.grid_column_count);
        mAdapter = new SportsAdapter(getContext());
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), gridCount));
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSportsViewModel.getAllSports().observe(getViewLifecycleOwner(), sports -> {
            mAdapter.setSportsData(sports);
        });
    }
}