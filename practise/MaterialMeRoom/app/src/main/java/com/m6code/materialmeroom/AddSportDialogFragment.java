package com.m6code.materialmeroom;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AddSportDialogFragment extends BottomSheetDialogFragment {

    // Customize parameter argument names
    private static final String TAG = AddSportDialogFragment.class.getSimpleName();

    private TextView mTitle;
    private TextView mInfo;
    private FloatingActionButton fab;
    private SportViewModel viewModel;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_add_sport_dialog, container, false);
        mTitle = root.findViewById(R.id.title_edit_text);
//        mInfo = root.findViewById(R.id.sportInfo_editText);
        fab = root.findViewById(R.id.done_fab);
        viewModel = new ViewModelProvider(this).get(SportViewModel.class);

        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        fab.setOnClickListener(v -> {
            getInput_updateSportsList();
        });
    }

    private void getInput_updateSportsList() {
        if (!TextUtils.isEmpty(mTitle.getText().toString())) {
            String title = mTitle.getText().toString();
            String info = "Here is some " + title + " news!";

            // TODO : Present  the user with images to select from
            // TODO : use file picker to pick and store image in storage
            //String imageName = title.replaceAll("\\s+", "").toLowerCase();

            // Add note to the database
            Sport sport = new Sport(title, info, "img_soccer");
            viewModel.insert(sport);

            mTitle.setText("");
        }else {
            // Hide AddSportDialogFragment
        }
    }
}