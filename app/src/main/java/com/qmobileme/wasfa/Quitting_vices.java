package com.qmobileme.wasfa;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import static android.support.constraint.Constraints.TAG;

//public class Quitting_vices extends AppCompatActivity {
public class Quitting_vices extends YouTubeBaseActivity {

    LinearLayout ll_back;
    VideoView videoView;
    TextView tv_start;
//    String video_url = "https://r2---sn-h5moxug0-3a4l.googlevideo.com/videoplayback?ms=au%2Crdu&id=o-AIuCpOoaw-n8TLmbEHFH8ulc0uFOr801QPMEgQqir_pF&ei=6IUbXPWzJNSGyQWOyICoCQ&mv=m&mt=1545307525&dur=88.212&pl=21&source=youtube&ip=5.23.103.98&key=yt6&mn=sn-h5moxug0-3a4l%2Csn-n8v7znlr&mm=31%2C29&initcwndbps=706250&fvip=7&sparams=clen%2Cdur%2Cei%2Cgir%2Cid%2Cinitcwndbps%2Cip%2Cipbits%2Citag%2Clmt%2Cmime%2Cmm%2Cmn%2Cms%2Cmv%2Cnh%2Cpl%2Cratebypass%2Crequiressl%2Csource%2Cexpire&nh=%2CIgpwcjAyLnN2bzA2KgkxMjcuMC4wLjE&expire=1545329224&lmt=1332979788044894&requiressl=yes&clen=3744769&mime=video%2Fmp4&gir=yes&ipbits=0&ratebypass=yes&c=WEB&itag=18&signature=D451941C8B6D0330177B8872904B79699A9E2A27.B9A58FFBA04A132054E243D82F64E1BA5626A004&video_id=0gGMqjeeiJY&title=Posermocap+Sci+Fi+Moves+Volume+2.mp4";
    String video_url = "https://www.youtube.com/watch?v=5aVU_0a8-A4";
    final String developer_key = "AIzaSyBj_L_YFAcothZ2LLH2EdEzoDr2ZqWJ9ok";
    String video_id;



    String string = "004-034556";
    String[] parts = video_url.split("v=");
    String part1 = parts[0]; // 004
    String part2 = parts[1]; // 034556
    YouTubePlayerView youTubePlayerView;
    YouTubeThumbnailView videoThumbnailImageView;


    MediaController mediaControler;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quitting_vices);
//        getSupportActionBar().hide();

//        actionbar();
        initialize();
        view_cliks();

        Log.i("before- V: " + part1 , " after V= " + part2);






        videoThumbnailImageView = findViewById(R.id.video_thumbnail_image_view);
        videoThumbnailImageView.initialize(developer_key, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
                //when initialization is sucess, set the video id to thumbnail to load
                youTubeThumbnailLoader.setVideo("5xVh-7ywKpE");

                youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                    @Override
                    public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                        //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        youTubeThumbnailLoader.release();
                    }

                    @Override
                    public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                        //print or show error when thumbnail load failed
                        Log.e(TAG, "Youtube Thumbnail Error");
                    }
                });
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //print or show error when initialization failed
                Log.e(TAG, "Youtube Initialization Failure");

            }
        });







//        youTubePlayerView.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
        youTubePlayerView.initialize(developer_key,
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {


                        youTubePlayer.loadVideo("5xVh-7ywKpE");
                        youTubePlayer.play();
//                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);

                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });


        videoThumbnailImageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                videoThumbnailImageView.setVisibility(View.GONE);
                youTubePlayerView.setVisibility(View.VISIBLE);
            }
        });











    }

    public void initialize()
    {
        ll_back = findViewById(R.id.ll_back);
        videoView = findViewById(R.id.vv_videoview);
        tv_start = findViewById(R.id.tv_start);
        youTubePlayerView = findViewById(R.id.player);

//        Bitmap thumb = ThumbnailUtils.createVideoThumbnail(video_url,
//                MediaStore.Images.Thumbnails.MINI_KIND);
//
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(thumb);



        mediaControler = new MediaController(this);
//        createThumbnailAtTime(video_url, 2);
        mediaControler.setAnchorView(videoView);
        videoView.setMediaController(mediaControler);
        videoView.requestFocus();
        videoView.seekTo( 2000 );

//        https://stackoverflow.com/questions/13814055/how-to-play-youtube-videos-in-android-video-view

        videoView.getDuration();
        videoView.setVideoPath("http://www.youtube.com/embed/" + part2 + "?autoplay=1&vq=small");

        
        videoView.start();



    }

    private Bitmap createThumbnailAtTime(String filePath, int timeInSeconds){
        MediaMetadataRetriever mMMR = new MediaMetadataRetriever();
        mMMR.setDataSource(filePath);
        //api time unit is microseconds
        return mMMR.getFrameAtTime(timeInSeconds*1000000, MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
    }


    public void view_cliks()
    {
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Quitting_vices.this, QuitSmokingActivity.class);
                startActivity(intent);

            }
        });

    }

    public void actionbar()
    {
//            getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
//            getSupportActionBar().setCustomView(R.layout.health_quitting_extra);
            ll_back = findViewById(R.id.ll_back);

            ll_back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(Quitting_vices.this, "Clicked: ", Toast.LENGTH_SHORT).show();
        }
    });
}

    
}
