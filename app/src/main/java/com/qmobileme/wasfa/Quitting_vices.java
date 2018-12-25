package com.qmobileme.wasfa;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
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


public class Quitting_vices extends AppCompatActivity {

    LinearLayout ll_back;
    VideoView videoView;
    TextView tv_start, tv_quit_smoking;
    ProgressDialog progressDialog;

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

        getSupportActionBar().hide();
        setContentView(R.layout.activity_quitting_vices);
        progressDialog = new ProgressDialog(Quitting_vices.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        initialize();
        view_cliks();


        videoThumbnailImageView.setVisibility(View.VISIBLE);


        YoutubeThumbnail();


 }



 public void YoutubeThumbnail()
 {

     videoThumbnailImageView.initialize(developer_key, new YouTubeThumbnailView.OnInitializedListener() {
         @Override
         public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, final YouTubeThumbnailLoader youTubeThumbnailLoader) {
             //when initialization is sucess, set the video id to thumbnail to load
             youTubeThumbnailLoader.setVideo("7UUPiCVbZhE");

             youTubeThumbnailLoader.setOnThumbnailLoadedListener(new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
                 @Override
                 public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                     //when thumbnail loaded successfully release the thumbnail loader as we are showing thumbnail in adapter
                        progressDialog.dismiss();
                     youTubeThumbnailLoader.release();
                 }

                 @Override
                 public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {
                     //print or show error when thumbnail load failed
                     progressDialog.dismiss();
                     Log.e(TAG, "Youtube Thumbnail Error");
                 }
             });
         }

         @Override
         public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
             //print or show error when initialization failed
             progressDialog.dismiss();
             Log.e(TAG, "Youtube Initialization Failure");

         }
     });


     videoThumbnailImageView.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View arg0) {


             Intent intent = new Intent(getApplicationContext(), YoutubeVideo.class);
             startActivity(intent);



         }
     });



 }




    public void initialize()
    {
        ll_back = findViewById(R.id.ll_back);
        videoView = findViewById(R.id.vv_videoview);
        tv_start = findViewById(R.id.tv_start);
        youTubePlayerView = findViewById(R.id.player);
        videoThumbnailImageView = findViewById(R.id.video_thumbnail_image_view);
        tv_quit_smoking = findViewById(R.id.tv_quit);


        mediaControler = new MediaController(this);
        mediaControler.setAnchorView(videoView);
        videoView.setMediaController(mediaControler);
        videoView.requestFocus();
        videoView.seekTo( 2000 );



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


        tv_quit_smoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Alarm.class);
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
