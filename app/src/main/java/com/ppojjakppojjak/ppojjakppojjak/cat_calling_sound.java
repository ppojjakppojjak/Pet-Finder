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

public class cat_calling_sound extends AppCompatActivity {

    Button btn1, btn2;
    MediaPlayer mp;
    TextView text;
    public static String BOARD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_calling_sound);

        final Spinner spinner = (Spinner)findViewById(R.id.cat_category_bar);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);

        ArrayAdapter adapter  = ArrayAdapter.createFromResource(this,R.array.cat_sound_list,android.R.layout.simple_spinner_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                BOARD = (String) spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mp = MediaPlayer.create(cat_calling_sound.this, R.raw.cat_come_on);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp != null){
                    mp.start();
                    mp.setLooping(true);
                    Thread();
                } else {
                    mp = MediaPlayer.create(cat_calling_sound.this, R.raw.cat_come_on);
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


    }

    public void Thread(){
        Runnable task = new Runnable(){


            public void run(){
                // 음악이 재생중일때
                while(mp.isPlaying()){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
