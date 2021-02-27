package com.m6code.materialmeroom2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private Button mImagePickerButton;

    private static final int SELECT_PICTURE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_edit_sport, container, false);
        mTitle = root.findViewById(R.id.title_edit_text);
        mFab = root.findViewById(R.id.done_fab);
        mImagePickerButton = root.findViewById(R.id.button_image_picker);
        // for insert / update of sports
        mViewModel = new ViewModelProvider(this).get(SportsViewModel.class);

        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFab.setOnClickListener(v -> {
            getUpdateSport();
        });

        mImagePickerButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image")
                    , SELECT_PICTURE);
        });
    }

    // TODO: RECEIVE Selected image uri
    // TODO : Store Image to database using MainActivity

    void goHome() {
        NavHostFragment.findNavController(AddEditSportDialogFragment.this)
                .navigate(R.id.action_addEditSportDialogFragment_to_sportsFragment);
    }

    void getUpdateSport() {
        if (!TextUtils.isEmpty(mTitle.getText().toString())) {
            String title = mTitle.getText().toString();
            String info = "Here is some " + title + " news!!!";

            // TODO: Present the user with preloaded images to select from
            // TODO: Present the user with image/file picker to pick an image from gallery

            // TODO: Pass title to MainActivity. Use View Model from MainActivity to create new sport with image.

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