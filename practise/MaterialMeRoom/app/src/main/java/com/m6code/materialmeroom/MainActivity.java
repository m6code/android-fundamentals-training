package com.m6code.materialmeroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    //    private LiveData<List<Sport>> mSportsData;
    private SportsAdapter mAdapter;
    private SportViewModel sportViewModel;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize fragment
        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .setReorderingAllowed(true)
//                    .add(R.id.addEditFragment, AddSportDialogFragment.class, null)
//                    .commit();
        }

        // Get the integer value from the integers.xml
        int gridColumnCount = getResources()
                .getInteger(R.integer.grid_column_count);

        sportViewModel = new ViewModelProvider(this).get(SportViewModel.class);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new SportsAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        //mSportsData = new ArrayList<>();

        sportViewModel.getmAllSports().observe(this, sports -> {
            mAdapter.setSportsList(sports);
        });
        // Get the data.
        initializeData();

        int swipDirs;
        if (gridColumnCount > 1) {
            swipDirs = 0;
        } else {
            swipDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

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
                sportViewModel.delete(mAdapter.getWordAtPosition(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();

//                mSportsData.remove(viewHolder.getAdapterPosition());
                // Animate the deletion property
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * Initialize the sports data from resources.
     */
    private void initializeData() {
//        // Get the resources from the XML file.
//        String[] sportsList = getResources()
//                .getStringArray(R.array.sports_titles);
//        String[] sportsInfo = getResources()
//                .getStringArray(R.array.sports_info);
//        TypedArray sportsImageResources =
//                getResources().obtainTypedArray(R.array.sports_images);
//
//        // Clear the existing data (to avoid duplication).
//        mSportsData.clear();
//
//        // Create the ArrayList of Sports objects with titles and
//        // information about each sport.
//        for (int i = 0; i < sportsList.length; i++) {
//            mSportsData.add(new Sport(sportsList[i], sportsInfo[i],
//                    sportsImageResources.getResourceId(i, 0)));

//        if (mSportsData != null) mSportsData.clear();


//    }

//        sportsImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    public void addSports(View view) {
        // TODO: should launch a fragment that creates sport and insert into database
        // Show image picker from default image
        // show gallery image picker for user image

//        getSupportFragmentManager().beginTransaction()
//                .setReorderingAllowed(true)
//                .add(R.id.addEditFragment, AddSportDialogFragment.class, null)
//                .commit();

//        Intent intent = new Intent(this, AddSportDialogFragment.class);
//        startActivity(intent);

        // TODO: Show show a dialog
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Add a Sport")
                .setTitle("New Sport");

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO: Add Sport to Database
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel and hide dialog;
                if (dialog != null) dialog.dismiss();
            }
        });

// 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        dialog = builder.create();

        dialog.show();
    }
}