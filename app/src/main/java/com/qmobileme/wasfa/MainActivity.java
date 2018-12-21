package com.qmobileme.wasfa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qmobileme.wasfa.Extra.FixedSpeedScroller;
import com.qmobileme.wasfa.Extra.Sliding_Image_adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {


    public static final String PREFS_NAME = "WASFA";
    Timer swipeTimer;
    SharedPreferences.Editor write;
    SharedPreferences sharedPreferences;


    public MainActivity() {
        titlearray = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        sharedPreferences =getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        swipeTimer = new Timer();
        write = sharedPreferences.edit();
//        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(getResources().getDrawable(R.drawable.green_bg));

        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.action_bar);
//        ImageView iv_back = findViewById(R.id.iv_back);
//
//
//        iv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "back pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
                init();

                }

    @SuppressLint("StaticFieldLeak")
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.silver,R.drawable.gold,R.drawable.diamond, R.drawable.bronze };
    private ArrayList<Integer> ImagesArray = new ArrayList<>();


    private static final String[] titles = {String.valueOf(R.string.silver_title),String.valueOf(R.string.golden_title),
            String.valueOf(R.string.diamond_title), String.valueOf(R.string.bronze_title)};
    private ArrayList<String> titlearray;


    private static final String[] messages = {String.valueOf(R.string.silver_text),String.valueOf(R.string.golden_text),
            String.valueOf(R.string.diamond_text),   String.valueOf(R.string.bronze_text)};


    private ArrayList<String> messagearray = new ArrayList<>();

    public void init() {

        for(int i = 0; IMAGES.length > i; i++)
        {
            ImagesArray.add(IMAGES[i]);
        }


        for(int i=0;i<titles.length;i++)
        {
            titlearray.add(titles[i]);
        }

        for(int i=0;i<messages.length;i++)
        {
            messagearray.add(messages[i]);
        }

        Log.i("TITLES1", "title " + titlearray.toString() +" ");



        Log.i("TITLES1", "message " + messagearray.toString() +" ");
        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setClipToPadding(false);
        mPager.setPadding(165, 0, 165 , 0);
        try {
            Field mScroller=ViewPager.class.getDeclaredField("mScroller");
            Interpolator sInterpolator = new AccelerateInterpolator();
            FixedSpeedScroller scroller = new FixedSpeedScroller(mPager.getContext(),sInterpolator);
            mScroller.setAccessible(true);
            mScroller.set(mPager, scroller);

        } catch (NoSuchFieldException e) {
        } catch (IllegalArgumentException e) {
        } catch (IllegalAccessException e) {
        }

        mPager.setAdapter(new Sliding_Image_adapter(MainActivity.this,ImagesArray));

        final TabLayout indicator = (TabLayout)
                findViewById(R.id.indicator);           //is the layou

        indicator.setupWithViewPager(mPager, true);
        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);


            }
        };
//        final Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);

            }
        }, 4000, 4000);

        // Pager listener over indicator


        indicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                currentPage = tab.getPosition();

                LayoutInflater li = LayoutInflater.from(getApplicationContext());
                View layout = li.inflate(R.layout.item_image_slider_layout, null, false);
                TextView textView = (TextView) layout.findViewById(R.id.tv_intro_title);

                if(currentPage == 0 )
                {

                    Log.i("PAGENO11111","Running" + textView.getText());
                    Intent inte = new Intent(MainActivity.this, Quitting_vices.class);
                    startActivity(inte);

                }
                if (currentPage == 1)
                {

                    Log.i("PAGENO22222","Running");
                }

              else  if (currentPage == 2)
                {
                    Log.i("PAGENO3333","Running");

                }

                else  if (currentPage == 3)
                {
                    Log.i("PAGENO4444","Running");

//                    write.putString("first","NO");
//                    write.commit();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


}
