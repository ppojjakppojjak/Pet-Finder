package com.ppojjakppojjak.ppojjakppojjak;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ChoosePetFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_pet, null);
        Button btnChooseDog = view.findViewById(R.id.btn_choose_dog);
        Button btnChooseCat = view.findViewById(R.id.btn_choose_cat);

        btnChooseDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenuFragment mainMenuFragment = new MainMenuFragment();
                Bundle arguments = new Bundle();
                arguments.putString("key", "dog");
                mainMenuFragment.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, mainMenuFragment);
                fragmentTransaction.commit();
            }
        });

        btnChooseCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMenuFragment mainMenuFragment = new MainMenuFragment();
                Bundle arguments = new Bundle();
                arguments.putString("key", "cat");
                mainMenuFragment.setArguments(arguments);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame, mainMenuFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
