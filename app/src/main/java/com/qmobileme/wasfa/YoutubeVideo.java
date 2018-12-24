package com.qmobileme.wasfa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeVideo extends YouTubeBaseActivity {

    YouTubePlayerView youTubePlayerView;
    final String developer_key = "AIzaSyBj_L_YFAcothZ2LLH2EdEzoDr2ZqWJ9ok";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_video);
        youTubePlayerView = findViewById(R.id.player);

        YoutbuePlayer();
    }


    public void YoutbuePlayer()
    {

        youTubePlayerView.initialize(developer_key,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {


                        youTubePlayer.loadVideo("hip-_JbR888");
                        youTubePlayer.play();
//                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);

                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }
}
