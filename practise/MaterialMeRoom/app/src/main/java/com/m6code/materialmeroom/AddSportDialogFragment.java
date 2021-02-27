package com.m6code.materialmeroom;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AddSportDialogFragment extends DialogFragment {

    // Customize parameter argument names
    private static final String TAG = AddSportDialogFragment.class.getSimpleName();

    private TextView mTitle;
    //    private FloatingActionButton fab;
    private SportViewModel viewModel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        // 1. Instantiate an <code><a href="/reference/android/app/AlertDialog.Builder.html">AlertDialog.Builder</a></code> with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // 2. Chain together various setter methods to set the dialog characteristics
//        builder.setMessage("Add a Sport")
//                .setTitle("New Sport");
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View root = inflater.inflate(R.layout.fragment_add_sport_dialog,null);

        builder.setView(inflater.inflate(R.layout.fragment_add_sport_dialog, null))
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO: Add Sport to Database
                        getInput_updateSportsList();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Cancel and hide dialog;
                AddSportDialogFragment.this.getDialog().cancel();
            }
        });

        // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
        return builder.create();
    }


//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//
//        View root = inflater.inflate(R.layout.fragment_add_sport_dialog, container, false);
//        mTitle = root.findViewById(R.id.title_edit_text);
////        mInfo = root.findViewById(R.id.sportInfo_editText);
////        fab = root.findViewById(R.id.done_fab);
//        viewModel = new ViewModelProvider(this).get(SportViewModel.class);
//
//        return root;
//
//    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
////        fab.setOnClickListener(v -> {
////            getInput_updateSportsList();
////        });
//    }

    private void getInput_updateSportsList() {

        viewModel = new ViewModelProvider(this).get(SportViewModel.class);

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
        } else {
            // Hide AddSportDialogFragment
        }
    }
}