package com.ppojjakppojjak.ppojjakppojjak;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainMenucatFragment extends Fragment {
    Bundle bundle = getArguments();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, null);

        CardView cardView1 = view.findViewById(R.id.cardview_1);
        CardView cardView2 = view.findViewById(R.id.cardview_2);
        CardView cardView3 = view.findViewById(R.id.cardview_3);
        CardView cardView4 = view.findViewById(R.id.cardview_4);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                startActivity(intent);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = getArguments();
                if(arguments.getString("key") == "dog") {
                    Intent intent = new Intent(getActivity(), dog_calling_sound.class);
                    startActivity(intent);
                } else if(arguments.getString("key") == "cat") {
                    Intent intent = new Intent(getActivity(), cat_calling_sound.class);
                    startActivity(intent);
                }
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = getArguments();
                if(arguments.getString("key") == "dog") {
                    Intent intent = new Intent(getActivity(), Allure_activity.class);
                    startActivity(intent);
                } else if(arguments.getString("key") == "cat") {
                    Intent intent = new Intent(getActivity(), Allure_activity_cat.class);
                    startActivity(intent);
                }
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arguments = getArguments();
                if(arguments.getString("key") == "dog") {
                    Intent intent = new Intent(getActivity(), FindDogPosterActivity.class);
                    startActivity(intent);
                } else if(arguments.getString("key") == "cat") {
                    Intent intent = new Intent(getActivity(), FindCatPosterActivity.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
