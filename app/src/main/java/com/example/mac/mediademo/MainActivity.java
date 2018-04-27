package com.example.mac.mediademo;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private ImageView show_img;
    private ImageButton play;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setOnClick();
    }


    private void init(){
        show_img = findViewById(R.id.show_img);
        play = findViewById(R.id.play);
        FMediaPlayer.setVideoPreView1(this,show_img,"http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4");
    }

    private void setOnClick(){
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FullActivity.class);
                startActivity(intent);
            }
        });
    }

}

