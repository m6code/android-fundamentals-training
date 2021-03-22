package com.m6code.materialmeroom2.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
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

        int swipDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipDirs) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                // TODO: swap on slide
//                Collections.swap(mSportsData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Delete item from list
                // Delete sport from database on swipe left or right
                Sport sport = mAdapter.getSportAtPosition(viewHolder.getAdapterPosition());
                int position = viewHolder.getAdapterPosition();
                mSportsViewModel.delete(sport);

                //Toast.makeText(getContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
//                mSportsData.remove(viewHolder.getAdapterPosition());

                // Animate the deletion property
//                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                mAdapter.removeSport(position);

                Snackbar.make(root, sport.getTitle() + " Deleted from Database",
                        Snackbar.LENGTH_LONG)
                        .setAction("Undo", view -> {
                            // Undo delete
                            mAdapter.restoreSport(position, sport);
                            mSportsViewModel.insert(sport);
                        })
                        .setActionTextColor(Color.YELLOW)
                        .show();
            }
        });

        helper.attachToRecyclerView(mRecyclerView);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSportsViewModel.getAllSports().observe(getViewLifecycleOwner(), sports -> {
            mAdapter.setSportsData(sports);
        });
    }
}