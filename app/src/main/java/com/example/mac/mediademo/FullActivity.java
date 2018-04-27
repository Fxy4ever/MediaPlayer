package com.example.mac.mediademo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * 全屏界面
 */

public class FullActivity extends AppCompatActivity {
    private ImageButton play,fullScreen;
    private Button back;
    private FMediaPlayer player;
    private SurfaceView surfaceView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private TextView currentTime;
    private TextView totalTime;
    private boolean isPlay = false;
    private boolean isLANDSCAPE=true;//是否为竖屏
    private ViewGroup.LayoutParams mVideoViewLayoutParams;
    private RelativeLayout mVideoLayout;
    private boolean isFirstLoad=true;
    private int preHeight;
    private int preWidth;
    private LinearLayout toolbar;
    private LinearLayout control;
    private boolean isTouchSV=false;
    private boolean isFullScreen=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
        init();
        setOnClick();
    }




    private void init(){
        play = findViewById(R.id.playBtn2);
        back = findViewById(R.id.full_back);
        fullScreen = findViewById(R.id.screenBtn2);
        progressBar = findViewById(R.id.load_bar2);
        seekBar = findViewById(R.id.seekBar2);
        toolbar = findViewById(R.id.control_toolbar);
        control = findViewById(R.id.control_layout2);
        surfaceView = findViewById(R.id.surface_view2);
        currentTime = findViewById(R.id.curr_time2);
        totalTime = findViewById(R.id.count_time2);
        mVideoLayout = findViewById(R.id.video_layout2);

        player = new FMediaPlayer()
                .setContext(this)
                .setSurfaceView(surfaceView)
                .setSeekBar(seekBar)
                .setProgressBar(progressBar)
                .setTextview(currentTime,totalTime)
                .setVideoPreView()
                .play();
    }

    private void setOnClick(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                player.createMediaPlayer();
            }
        },1000);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isPlay){
                    player.startMediaPlayer();
                    play.setBackgroundResource(R.mipmap.pause);
                    isPlay=true;
                }else{
                    player.pauseMediaPlayer();
                    play.setBackgroundResource(R.mipmap.play);
                    isPlay=false;
                }
            }
        });

        fullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLANDSCAPE){
                    FullActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    isLANDSCAPE=false;
                    isFullScreen=true;
                }else{
                    FullActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isLANDSCAPE=true;
                    isFullScreen=false;
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFullScreen){
                    FullActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    isLANDSCAPE=true;
                    isFullScreen=false;
                }else{
                    Intent intent = new Intent(FullActivity.this,MainActivity.class);
                    startActivity(intent);
                    FullActivity.this.finish();
                }
            }
        });
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTouchSV){
                    control.setVisibility(View.GONE);
                    toolbar.setVisibility(View.GONE);
                    isTouchSV=true;
                }else{
                    control.setVisibility(View.VISIBLE);
                    toolbar.setVisibility(View.VISIBLE);
                    isTouchSV=false;
                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int rot = getWindowManager().getDefaultDisplay().getRotation();
        mVideoViewLayoutParams = mVideoLayout.getLayoutParams();
        if(isFirstLoad){
            preHeight = mVideoViewLayoutParams.height;//保存初始界面的长宽;
            preWidth  = mVideoViewLayoutParams.width;
            isFirstLoad=false;
        }
        if(rot == Surface.ROTATION_90 || rot == Surface.ROTATION_270){
            mVideoViewLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoViewLayoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mVideoLayout.setLayoutParams(mVideoViewLayoutParams);
        }else if(rot == Surface.ROTATION_0){
            mVideoViewLayoutParams.height = preHeight;
            mVideoViewLayoutParams.width = preWidth;
            mVideoLayout.setLayoutParams(mVideoViewLayoutParams);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stopMediaPlayer();
        player.releaseMediaPlayer();
    }
}
