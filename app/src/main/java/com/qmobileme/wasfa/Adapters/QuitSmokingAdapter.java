package com.qmobileme.wasfa.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qmobileme.wasfa.Extra.Item;
import com.qmobileme.wasfa.R;

import java.util.ArrayList;






public class QuitSmokingAdapter extends  RecyclerView.Adapter<QuitSmokingAdapter.ViewHolder> {

    ClickListener clickListener;
    private transient ArrayList<Item> listItemss;
    private Context ctx;

    public QuitSmokingAdapter(Context activity, ArrayList<Item> data) {
        ctx = activity;
        listItemss = data;
        Log.i("DATAIS " , String.valueOf(listItemss));
    }


    //click listener
    public void setOnClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;

    }


    //inflate the XML
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.recylceritems, parent, false);


        ViewHolder viewHolder = new ViewHolder(ctx, contactView);
        return viewHolder;
    }


    //bind the View for data (Using Getter Setter)
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Item data = listItemss.get(position);
        holder.tvTitle.setText(data.getTitle());

        holder.iv_image.setBackground(ctx.getResources().getDrawable(R.drawable.back));
//        holder.iv_image.setBackground(ctx.getResources().getDrawable(data.getId()));






        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(position);



            }
        });
    }


    @Override
    public int getItemCount() {

//        return (listItems == null) ? 0 : listItems.size();
        Log.i("Size__" ,"Adapter " + listItemss.size() );


        return listItemss.size();
    }

    //interface of click listener
    public interface ClickListener {
        public void onClick(int position);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTitle;
        public Context ctx;

        ImageView iv_image;


        public ViewHolder(Context context, View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            iv_image = itemView.findViewById(R.id.iv_swipeimage);          //me
            ctx = context;
        }

        @Override
        public void onClick(View v) {

        }
    }
}

