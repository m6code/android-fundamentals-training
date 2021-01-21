package com.m6code.droidcafe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
//            }
//        });

        view.findViewById(R.id.donut).setOnClickListener(this::showDonutOrder);
        view.findViewById(R.id.icream).setOnClickListener(this::showIcreamOrder);
        view.findViewById(R.id.froyo).setOnClickListener(this::showFroyoOrder);
    }


    public void showDonutOrder(View view) {
        displayToast(getString(R.string.donut_order_message));
    }

    public void showIcreamOrder(View view){
        displayToast(getString(R.string.ice_cream_order_message));
    }

    public void showFroyoOrder(View view){
        displayToast(getString(R.string.froyo_order_message));
    }


    public void displayToast(String message){
        Toast.makeText(getContext(), message,
                Toast.LENGTH_SHORT).show();
    }
}