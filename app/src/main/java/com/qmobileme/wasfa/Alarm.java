package com.qmobileme.wasfa;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends AppCompatActivity {

    String to_date , from_date, to_time, from_time;
    int hour_from, mint_from, hour_to, mint_to;
     int to_hour_picker_calendar, to_mint_picker_calendar;
     int fromt_hour_picker_calendar, fromt_mint_picker_calendar;
     Timer from_timer, to_timer;
     Ringtone ringtone;
     AlarmManager alarmManager;
     Calendar temp_from_time, temp_to_time;
     LinearLayout ll_save_reminder;

    TextView tv_date_from, tv_date_to, tv_time_from, tv_time_to;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        from_timer = new Timer();
        to_timer = new Timer();
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        setContentView(R.layout.activity_alarm);
        temp_from_time = Calendar.getInstance();
        alarmManager = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        temp_to_time = Calendar.getInstance();
        getSupportActionBar().hide();
        ll_save_reminder = findViewById(R.id.ll_save);
        initialize();
        onclicks();


//        if(hour_from == Calendar.HOUR_OF_DAY && mint_from == Calendar.MINUTE)
//        {
//            AlarmNotification();
//        }



    }


    public void initialize()
    {
        tv_date_from = findViewById(R.id.tv_date_from);
        tv_date_to = findViewById(R.id.tv_date_to);
        tv_time_from = findViewById(R.id.tv_time_from);
        tv_time_to = findViewById(R.id.tv_time_to);
    }

    public void onclicks()
    {
        tv_date_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new DatePickerDialog(Alarm.this, date, from_date_calendar
                        .get(Calendar.YEAR), from_date_calendar.get(Calendar.MONTH),
                        from_date_calendar.get(Calendar.DAY_OF_MONTH)).show();



            }
        });

        tv_date_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DatePickerDialog(Alarm.this, to_date_dialog, to_date_calendar
                        .get(Calendar.YEAR), to_date_calendar.get(Calendar.MONTH),
                        to_date_calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_time_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    to_time_picker();
            }
        });

        tv_time_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from_time_picker();
            }
        });


        ll_save_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmNotification();
            }
        });


    }



  /*/ Start of FROM date picker*/
    Calendar from_date_calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            from_date_calendar.set(Calendar.YEAR, year);
            from_date_calendar.set(Calendar.MONTH, monthOfYear);
            from_date_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            from_date_lable();
        }

    };

    private void from_date_lable() {
        String myFormat = "MM/dd/yy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("Date_", sdf.format(from_date_calendar.getTime()));
        from_date = sdf.format(from_date_calendar.getTime());
        Log.i("Date_", from_date);
        tv_date_from.setText(sdf.format(from_date_calendar.getTime()));
    }


    public void from_time_picker()
    {


        final Calendar c = Calendar.getInstance();
        fromt_hour_picker_calendar = c.get(Calendar.HOUR_OF_DAY);
        fromt_mint_picker_calendar = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv_time_from.setText(hourOfDay + ":" + minute);
                        hour_from = hourOfDay;
                        mint_from = minute;
                       }
                }, fromt_hour_picker_calendar, fromt_mint_picker_calendar, false);
        timePickerDialog.show();


    }

    /*/ END of FROM date picker*/


    public void AlarmNotification()
    {
//        from_date_calendar.setTimeInMillis(System.currentTimeMillis());
        from_date_calendar.set(Calendar.HOUR_OF_DAY , hour_from);
        from_date_calendar.set(Calendar.MINUTE, mint_from);
        Intent intent = new Intent(getApplicationContext(), Alarm_Broadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, from_date_calendar.getTimeInMillis() , pendingIntent);
    }



  /**Start of TO date & time picker**/


  Calendar to_date_calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener to_date_dialog = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            to_date_calendar.set(Calendar.YEAR, year);
            to_date_calendar.set(Calendar.MONTH, monthOfYear);
            to_date_calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
          to_date_lable();
        }

    };

    private void to_date_lable() {
        String myFormat = "MM/dd/yy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("Date_", sdf.format(from_date_calendar.getTime()));
        from_date = sdf.format(from_date_calendar.getTime());
        Log.i("Date_", from_date);
        tv_date_to.setText(sdf.format(from_date_calendar.getTime()));
    }


    public void to_time_picker()
    {
        // Get Current Time

        final Calendar c = Calendar.getInstance();
        to_hour_picker_calendar = c.get(Calendar.HOUR_OF_DAY);
        to_mint_picker_calendar = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        //Timer for alarm

                        tv_time_to.setText(hourOfDay + ":" + minute);
                    }
                }, to_hour_picker_calendar, to_mint_picker_calendar, false);
        timePickerDialog.show();
    }

    /**END of TO date & time picker**/





}
