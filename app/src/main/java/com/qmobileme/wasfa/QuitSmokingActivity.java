package com.qmobileme.wasfa;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qmobileme.wasfa.Adapters.QuitSmokingAdapter;
import com.qmobileme.wasfa.Extra.Item;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class QuitSmokingActivity extends AppCompatActivity {



    private ArrayList<Item> listItems;
    private RecyclerView rvItems;

    QuitSmokingAdapter adapter;

    @SuppressLint("WrongConstant")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_quit_smoking);

        this.getWindow().
                setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();
        initialize();
        Items_of_adapter();
        adapter_click();


    }


    public void initialize()
    {
        rvItems=findViewById(R.id.recyclverview);
        listItems=new ArrayList<>();
         rvItems.setLayoutManager(new GridLayoutManager(this,4));
        adapter = new QuitSmokingAdapter(getApplicationContext(), listItems);
        rvItems.setAdapter(adapter);
     }

    public void adapter_click()
    {

        adapter.setOnClickListener(new QuitSmokingAdapter.ClickListener() {
            @Override
            public void onClick(int position) {

                DIALOGE();


                Toast.makeText(QuitSmokingActivity.this, "Clicked at: " +
                        "" +listItems.get(position), Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void Items_of_adapter()
    {
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));
        listItems.add(new Item("TEXT",0,R.drawable.back));
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));
        listItems.add(new Item("TEXT",0,R.drawable.back));
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));
        listItems.add(new Item("TEXT",0,R.drawable.back));
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));
        listItems.add(new Item("TEXT",0,R.drawable.back));
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));
        listItems.add(new Item("TEXT",0,R.drawable.back));
        listItems.add(new Item("TEXT",0 , R.drawable.ic_launcher_background));
        listItems.add(new Item("TEXT",0, R.drawable.ic_launcher_foreground));


    }

    TextView tv_day_of_quitting;
    int hours, final_hours;
    String temp;
    public void DIALOGE() {

        final android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(QuitSmokingActivity.this);
        LayoutInflater inflater = this.getLayoutInflater();

        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.dialog_quitting_smoking, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);


        Button btn_save  = dialogView.findViewById(R.id.btn_save_dialog);
        ImageView iv_minus = dialogView.findViewById(R.id.iv_minus);
        ImageView iv_plus = dialogView.findViewById(R.id.iv_plus);
        ImageView iv_cross = dialogView.findViewById(R.id.iv_cross);
        tv_day_of_quitting = dialogView.findViewById(R.id.tv_day_of_quitting);
        EditText et_packs = dialogView.findViewById(R.id.tv_packs_per_day);
        final TextView tv_hours_of_quitting = dialogView.findViewById(R.id.tv_hours_of_quitting);

        iv_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              hours = Integer.valueOf(tv_hours_of_quitting.getText().toString());
              final_hours = hours - 1;
              tv_hours_of_quitting.setText(String.valueOf(final_hours));
            }
        });

        iv_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hours = Integer.valueOf(tv_hours_of_quitting.getText().toString());
                final_hours = hours + 1;
                tv_hours_of_quitting.setText(String.valueOf(final_hours));
            }
        });

        tv_day_of_quitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuitSmokingActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();


            }
        });



        final android.support.v7.app.AlertDialog b = dialogBuilder.create();
        b.show();

        iv_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();

            }
        });


    }



    String DATE;

    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("Date_", sdf.format(myCalendar.getTime()));
        DATE = sdf.format(myCalendar.getTime());
        Log.i("Date_", DATE);
        tv_day_of_quitting.setText(sdf.format(myCalendar.getTime()));
    }



}
