package com.ppojjakppojjak.ppojjakppojjak;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction fr = fm.beginTransaction();

        fr.replace(R.id.frame, new ChoosePetFragment(), "choose_pet");
        fr.commit();
    }

    public void mOnClick(View v) {
        android.app.FragmentManager fm = getFragmentManager();
        android.app.Fragment fragment = fm.findFragmentById(R.id.frame);

        switch(v.getId()) {
            case R.id.btn_back:
                if (fragment == null) { finish(); }
                if (fragment.getTag() == "choose_pet") {
                    finish();
                } else if (fragment.getTag() == "main_menu") {
                    finish();
                } else {
                    FragmentTransaction tr = fm.beginTransaction();
                    ChoosePetFragment choosePetFragment = new ChoosePetFragment();
                    tr.replace(R.id.frame, choosePetFragment, "main_menu");
                    tr.commit();
                }
                break;
        }
    }
}
