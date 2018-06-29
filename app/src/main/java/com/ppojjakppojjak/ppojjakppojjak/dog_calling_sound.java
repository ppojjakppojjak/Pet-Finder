package com.ppojjakppojjak.ppojjakppojjak;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;

public class dog_calling_sound extends AppCompatActivity {

    Button btn1, btn2, btnBack;
    MediaPlayer mp;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_calling_sound);

        final Spinner spinner = (Spinner)findViewById(R.id.dog_category_bar);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btnBack = (Button)findViewById(R.id.button_back);

        final ArrayAdapter spinneradapter  = ArrayAdapter.createFromResource(this,R.array.dog_sound_list,android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinneradapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinneradapter.getItem(i).toString().equals("howling1")) {
                    if(mp!=null){
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(dog_calling_sound.this, R.raw.howling1);
                }
                else if(spinneradapter.getItem(i).toString().equals("howling2")) {
                    if(mp!=null){
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(dog_calling_sound.this, R.raw.howling2);
                }
                else if(spinneradapter.getItem(i).toString().equals("howling3")) {
                        if(mp!=null){
                            mp.stop();
                            mp.release();
                        }
                        mp = MediaPlayer.create(dog_calling_sound.this, R.raw.howling3);
                    }
                else if(spinneradapter.getItem(i).toString().equals("howling4")) {
                            if(mp!=null){
                                mp.stop();
                                mp.release();
                            }
                            mp = MediaPlayer.create(dog_calling_sound.this, R.raw.howling4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.start();
                    mp.setLooping(true);
                    Thread();
                } else {
                    mp = MediaPlayer.create(dog_calling_sound.this, R.raw.howling1);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                try
                {
                    mp.prepare();
                }
                catch(IOException ie)
                {
                    ie.printStackTrace();
                }
                mp.seekTo(0);
                //mp.release();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void Thread(){
        Runnable task = new Runnable(){


            public void run(){
                // 음악이 재생중일때
                try {
                    while (mp.isPlaying()) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
                catch (IllegalStateException e){
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
