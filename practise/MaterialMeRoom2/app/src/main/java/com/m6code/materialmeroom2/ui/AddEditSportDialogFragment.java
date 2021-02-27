package com.m6code.materialmeroom2.ui;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.m6code.materialmeroom2.R;
import com.m6code.materialmeroom2.model.Sport;

import static android.app.Activity.RESULT_OK;

public class AddEditSportDialogFragment extends BottomSheetDialogFragment {

    private static final String TAG = AddEditSportDialogFragment.class.getSimpleName();
    private TextInputEditText mTitle;
    private FloatingActionButton mFab;
    private SportsViewModel mViewModel;
    private Button mImagePickerButton;

    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;
    private Uri selectedImageUri;

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
            getUpdateSport(selectedImageUri);
        });

        mImagePickerButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
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

    void getUpdateSport(Uri imageUri) {
        if (!TextUtils.isEmpty(mTitle.getText().toString())) {
            String title = mTitle.getText().toString();
            String info = "Here is some " + title + " news!!!";

            // TODO: Present the user with preloaded images to select from
            // TODO: Present the user with image/file picker to pick an image from gallery

            // Add note to the database
            Sport sport = new Sport(title, info, imageUri.toString());
            mViewModel.insert(sport);
            mTitle.setText("");
            goHome();

        } else if (TextUtils.isEmpty(mTitle.getText().toString())) {
            goHome();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
//                selectedImagePath = getPath(selectedImageUri);
                Log.d(TAG, "onActivityResult: selected Image URI is: " + selectedImageUri.toString());
                getUpdateSport(selectedImageUri);
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
//    public String getPath(Uri uri) {
//        // just some safety built in
//        if (uri == null) {
//            // TODO perform some logging or show user feedback
//            return null;
//        }
//        // try to retrieve the image from the media store first
//        // this will only work for images selected from gallery
//        String[] projection = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContext().getContentResolver()
//                .query(uri, projection, null, null, null);
//        if (cursor != null) {
//            int column_index = cursor
//                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            cursor.moveToFirst();
//            String path = cursor.getString(column_index);
//            cursor.close();
//            return path;
//        }
//        // this is our fallback here
//        return uri.getPath();
//    }
}