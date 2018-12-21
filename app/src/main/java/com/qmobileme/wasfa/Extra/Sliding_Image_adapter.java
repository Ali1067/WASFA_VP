package com.qmobileme.wasfa.Extra;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qmobileme.wasfa.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

//import bringthings.codevilla.com.bringthings.R;

/**
 * Created by AQEEL on 3/8/2018.
 */

public class Sliding_Image_adapter extends PagerAdapter {


    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;
    private LinearLayout linearLayout;
    private Button btn_subscribe;


    //    public Sliding_Image_adapter(Context context,ArrayList<Integer> IMAGES) {
    public Sliding_Image_adapter(Context context, ArrayList<Integer> IMAGES) {

        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.item_image_slider_layout, view, false);

        assert imageLayout != null;

        final ImageView imageView = imageLayout  .findViewById(R.id.image);

//        if(IMAGES.get(position) == 3) {
//         String image=  " http://www.irfaa.com/uploads/GP_3664633_2018827000_72676767.gif";
//
            Glide.with(context)
                    .load(IMAGES.get(position))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.gold)
                            .centerCrop()
                            .dontAnimate()
                            .dontTransform())
                    .into(imageView);
//        }





        final TextView titleview = imageLayout .findViewById(R.id.tv_intro_title);
        final TextView messageview =  imageLayout   .findViewById(R.id.tv_intro_message);
        linearLayout = imageLayout.findViewById(R.id.ll_bgcolor);
        btn_subscribe = imageLayout.findViewById(R.id.btn_subscribe);
        
        btn_subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        if(position == 0)
        {
            titleview.setText(context.getResources().getString(R.string.silver_title));
            messageview.setText(context.getResources().getString(R.string.silver_text));
            linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_silver));
            btn_subscribe.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_silver));
        }
        else  if(position == 1)
        {
            titleview.setText(context.getResources().getString(R.string.golden_title));
            messageview.setText(context.getResources().getString(R.string.golden_text));
            linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_golden));
            btn_subscribe.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_golden));
        }
        else if  (position == 2)
        {
            titleview.setText(context.getResources().getString(R.string.diamond_title));
            messageview.setText(context.getResources().getString(R.string.diamond_text));
            linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_diamond));
            btn_subscribe.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_diamond));
        }
        else if  (position == 3)
        {
            titleview.setText(context.getResources().getString(R.string.bronze_title));
            messageview.setText(context.getResources().getString(R.string.bronze_text));
            linearLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_bronze));
            btn_subscribe.setBackground(ContextCompat.getDrawable(context, R.drawable.btn_bronze));
        }









        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}