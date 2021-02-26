package com.m6code.materialmeroom2.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.m6code.materialmeroom2.R;
import com.m6code.materialmeroom2.model.Sport;

public class AddEditSportDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = AddEditSportDialogFragment.class.getSimpleName();
    private TextInputEditText mTitle;
    private FloatingActionButton mFab;
    private SportsViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_edit_sport, container, false);
        mTitle = root.findViewById(R.id.title_edit_text);
        mFab = root.findViewById(R.id.done_fab);
        // for insert / update of sports
        mViewModel = new ViewModelProvider(this).get(SportsViewModel.class);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            mFab.setOnClickListener(v -> {
                getUpdateSport();
            });
    }

    void goHome(){
        NavHostFragment.findNavController(AddEditSportDialogFragment.this)
                .navigate(R.id.action_addEditSportDialogFragment_to_sportsFragment);
    }

    void getUpdateSport(){
        if (!TextUtils.isEmpty(mTitle.getText().toString())) {
            String title = mTitle.getText().toString();
            String info = "Here is some " + title + " news!!!";

            // TODO: Present the user with preloaded images to select from
            // TODO: Present the user with image/file picker to pick an image from gallery

            // Add note to the database
            Sport sport = new Sport(title, info, "img_soccer");
            mViewModel.insert(sport);
            mTitle.setText("");
            goHome();

        } else if (TextUtils.isEmpty(mTitle.getText().toString())) {
            goHome();
        }
    }
}