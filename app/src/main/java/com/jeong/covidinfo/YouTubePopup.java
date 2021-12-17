package com.jeong.covidinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Calendar;

public class YouTubePopup extends YouTubeBaseActivity {
    YouTubePlayerView PlayerView;
    Integer Todaynum;

    private String API_KEY = "AIzaSyBj7njW4DY-rR4KJWdao6cT-ruPr_bOD00";
    //private String Video_id = "dBACMLeMdIU";
    private String[] Video_id = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_popup);
        String[] Video_id = {"dBACMLeMdIU", "059c-VBnv2c", "bRa5KJEUOLg", "2aQH1zOnj4I", "OhVHjh9y5jc", "6uhFfkPoCV8", "oslHEme_vEA"};
        Calendar calendar = Calendar.getInstance();
        //요일에 따라 1~7을 반환해줌
        Todaynum = calendar.get(Calendar.DAY_OF_WEEK);
        StartVideo(Video_id);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    private void StartVideo(String[] Video_id) {
        PlayerView = findViewById(R.id.youtube_view);
        PlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener(){
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.cueVideo(Video_id[Todaynum-1]);
                }
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() { }
                    @Override
                    public void onLoaded(String s) {
                        youTubePlayer.play();
                    }
                    @Override
                    public void onAdStarted() { }
                    @Override
                    public void onVideoStarted() { }
                    @Override
                    public void onVideoEnded() { }
                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) { }
                });
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });

    }
}