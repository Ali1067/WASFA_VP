package com.qmobileme.wasfa;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends AppCompatActivity {

    String to_date , from_date, to_time, from_time;
    int hour_from =0, mint_from=0, hour_to=0, mint_to=0;
    int from_day, from_month, from_year, to_day, to_month, to_year;
     int to_hour_picker_calendar, to_mint_picker_calendar;
     int fromt_hour_picker_calendar, fromt_mint_picker_calendar;
     Timer from_timer, to_timer;
     Ringtone ringtone;
     AlarmManager alarmManager;
     Calendar temp_from_time, temp_to_time;
     LinearLayout ll_save_reminder;
     LinearLayout ll_mon, ll_tue, ll_wed, ll_thurs, ll_fri, ll_sat, ll_sun;
     CheckBox cb_mon, cb_tue, cb_wed, cb_thurs, cb_fri, cb_sat, cb_sun;
     TextView tv_date_from, tv_date_to, tv_time_from, tv_time_to, tv_text_repeat;
     LinearLayout tv_repeat;
     CheckBox cb_all_day;
     boolean b_mon, b_tue, b_wed, b_thur, b_fri, b_sat, b_sun;

     EditText et_title, et_description;
     SharedPreferences sharedPreferences;
     SharedPreferences.Editor write;
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
        sharedPreferences = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        write = sharedPreferences.edit();

        ll_save_reminder = findViewById(R.id.ll_save);
        initialize();
        onclicks();




    }


    public void initialize()
    {
        tv_date_from = findViewById(R.id.tv_date_from);
        tv_date_to = findViewById(R.id.tv_date_to);
        tv_time_from = findViewById(R.id.tv_time_from);
        tv_time_to = findViewById(R.id.tv_time_to);
        tv_repeat = findViewById(R.id.tv_repeat);
        cb_all_day = findViewById(R.id.cb_allday);
        tv_text_repeat = findViewById(R.id.tv_repeat_text);
        et_title = findViewById(R.id.et_alarm_title);
        et_description =  findViewById(R.id.et_alarm_description);





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



                if (!TextUtils.isEmpty((et_title.getText().toString())))
                {
                    String text = et_title.getText().toString();
                    write.putString("TITLE" , text);
                    if (!TextUtils.isEmpty((et_description.getText().toString())))
                    {
                        String text_des = et_description.getText().toString();
                        write.putString("DESCRIPTION" , text_des);
                        AlarmNotification();
                    }
                    else{
                        et_description.setError("Required");
                    }

                }
                else{
                    et_title.setError("Required");
                }


//                else
//                {
//                    et_description.setError("Required");
//                }
//                AlarmNotification();
            }
        });





        cb_all_day.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(cb_all_day.isChecked()){
                    Toast.makeText(Alarm.this, "ALL Day", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Alarm.this, "NOT ALL Day", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOGE_CALENDAR();
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
        String myFormat = "MM/dd/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("Date_", sdf.format(from_date_calendar.getTime()));
        from_date = sdf.format(from_date_calendar.getTime());
        Log.i("Date_", from_date);
        tv_date_from.setText(sdf.format(from_date_calendar.getTime()));



        String[] parts = from_date.split("/");
        from_month = Integer.parseInt(parts[0]);
        from_day = Integer.parseInt(parts[1]);
        from_year = Integer.parseInt(parts[2]);
        Log.i("DATE_FOR_ALARM" , "MONTH: " + from_month + " DAY: " + from_day + " YEAR: " + from_year);


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

        from_date_calendar.set(Calendar.DAY_OF_MONTH, from_day);
        from_date_calendar.set(Calendar.MONTH, from_month-1);
        from_date_calendar.set(Calendar.YEAR, from_year);

       Log.i("GettingDATE " , "FROM DATE " + from_date_calendar.getTime());

        to_date_calendar.clear();
        to_date_calendar.set(Calendar.HOUR_OF_DAY , hour_to);
        to_date_calendar.set(Calendar.MINUTE, mint_to);
        to_date_calendar.set(Calendar.DAY_OF_MONTH, to_day);
        to_date_calendar.set(Calendar.MONTH, to_month-1);
        to_date_calendar.set(Calendar.YEAR, to_year);
        Log.i("GettingDATE" , "HOUR " + hour_to + " MINT " + mint_to + " day " +to_day
        +" month " +to_month + " year" + to_year) ;
        Log.i("GettingDATE " , "TO DATE " + to_date_calendar.getTime());
        Log.i("GettingDATE " , "TO DATE MILLI " + to_date_calendar.getTimeInMillis());
        Calendar calendar = Calendar.getInstance();

        Intent intent = new Intent(getApplicationContext(), Alarm_Broadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0, intent, PendingIntent.FLAG_UPDATE_CURRENT);



        Log.i("GettingDATE " , "FROM CALENDAR DATE " + from_date_calendar.getTime());
        Log.i("GettingDATE " , "FROM CALENDAR DATE Milli " + from_date_calendar.getTimeInMillis());
        Log.i("GettingDATE " , "Current CALENDAR DATE " + calendar.getTime());
        Log.i("GettingDATE " , "Current MILLI CALENDAR DATE " + from_date_calendar.getTimeInMillis());


            //Future or todays date.
        if(from_date_calendar.getTimeInMillis() >= calendar.getTimeInMillis())

        {

//            if(to_date_calendar.getTimeInMillis() <= from_date_calendar.getTimeInMillis())
//            {


            alarmManager.set(AlarmManager.RTC_WAKEUP, from_date_calendar.getTimeInMillis() , pendingIntent);
                Toast.makeText(this, "SHould Work", Toast.LENGTH_SHORT).show();
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,from_date_calendar.getTimeInMillis() ,
                        3*1000*3600,pendingIntent);
                    // 3  * 1000 = 3 sec -> 3 x3600 = 3 hours -->repeat after 3 hours





//            }
        }

                        //Passed Date.
//        else if (from_date_calendar .getTimeInMillis()< calendar.getTimeInMillis())
        else
        {

            Toast.makeText(this, "SHould not Work", Toast.LENGTH_SHORT).show();
        }

            write.commit();


//        Log.i("ALARM_TIME" , " " + from_date_calendar.getTimeInMillis());
//        Intent intent = new Intent(getApplicationContext(), Alarm_Broadcast.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, from_date_calendar.getTimeInMillis() , pendingIntent);


//        alarmManager.setRepeating("","","","","");
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
        String myFormat = "MM/dd/yyyy";

        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.i("Date_", sdf.format(to_date_calendar.getTime()));
        to_date = sdf.format(to_date_calendar.getTime());
        Log.i("Date_", to_date);
        tv_date_to.setText(sdf.format(to_date_calendar.getTime()));

        String[] parts = to_date.split("/");
        to_month = Integer.parseInt(parts[0]);
        to_day = Integer.parseInt(parts[1]);
        to_year = Integer.parseInt(parts[2]);
        Log.i("TO_DATE_FOR_ALARM" , "MONTH: " + to_month + " DAY: " + to_day + " YEAR: " + to_year);


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

                        hour_to = hourOfDay;
                        mint_to = minute;

                        tv_time_to.setText(hourOfDay + ":" + minute);
                    }
                }, to_hour_picker_calendar, to_mint_picker_calendar, false);
        timePickerDialog.show();
    }

    /**END of TO date & time picker**/



    public void DIALOGE_CALENDAR() {

        final android.support.v7.app.AlertDialog.Builder dialogBuilder = new android.support.v7.app.AlertDialog.Builder(Alarm.this);
        LayoutInflater inflater = this.getLayoutInflater();

        @SuppressLint("InflateParams") final View dialogView = inflater.inflate(R.layout.days_dialog, null);
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(false);


        TextView tv_ok  = dialogView.findViewById(R.id.tv_OK_alarm);
        TextView tv_cancel = dialogView.findViewById(R.id.tv_cancel_alarm);
        ll_mon = dialogView.findViewById(R.id.ll_monday);
        ll_tue = dialogView.findViewById(R.id.ll_tuesday);
        ll_wed = dialogView.findViewById(R.id.ll_wednesday);
        ll_thurs = dialogView.findViewById(R.id.ll_thursday);
        ll_fri = dialogView.findViewById(R.id.ll_friday);
        ll_sat = dialogView.findViewById(R.id.ll_saturday);
        ll_sun = dialogView.findViewById(R.id.ll_sunday);

        cb_mon = dialogView.findViewById(R.id.cb_monday);
        cb_tue = dialogView.findViewById(R.id.cb_tuesday);
        cb_wed = dialogView.findViewById(R.id.cb_wednesday);
        cb_thurs = dialogView.findViewById(R.id.cbthursday);
        cb_fri = dialogView.findViewById(R.id.cb_friday);
        cb_sat = dialogView.findViewById(R.id.cb_saturday);
        cb_sun = dialogView.findViewById(R.id.cb_sunday);


        check_uncheck();

        String mon, tue, wed,thr,fri,sat,sun;
        cb_mon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_mon.setChecked(true);
                    write.putBoolean("MON", true);
                    write.putString("M", "M,");
                    Toast.makeText(Alarm.this, "Checked", Toast.LENGTH_SHORT).show();
                }
                else{
                    cb_mon.setChecked(false);
                    write.putBoolean("MON" , false);
                    write.putString("M", "");
                    Toast.makeText(Alarm.this, "Unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        cb_tue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    if(isChecked)
                    {
                        cb_tue.setChecked(true);
                        write.putBoolean("TUE", true);
                        write.putString("TU", "TU,");
                    }
                    else{
                        cb_tue.setChecked(false);
                        write.putBoolean("TUE" , false);
                        write.putString("TU", "");

                    }
            }
        }});


        cb_wed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_wed.setChecked(true);
                    write.putBoolean("WED", true);
                    write.putString("W", "W,");
                }
                else{
                    cb_wed.setChecked(false);
                    write.putBoolean("WED" , false);
                    write.putString("W", "");

                }
            }
        });



        cb_thurs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_thurs.setChecked(true);
                    write.putBoolean("THR", true);
                    write.putString("TH", "TH,");
                }
                else{
                    cb_thurs.setChecked(false);
                    write.putBoolean("THR" , false);
                    write.putString("TH", "");

                }
            }
        });



        cb_fri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_fri.setChecked(true);
                    write.putBoolean("FRI", true);
                    write.putString("F", "F,");
                }
                else{
                    cb_fri.setChecked(false);
                    write.putBoolean("FRI" , false);
                    write.putString("F", "");

                }
            }
        });



        cb_sat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_sat.setChecked(true);
                    write.putBoolean("SAT", true);
                    write.putString("S", "S,");
                }
                else{
                    cb_sat.setChecked(false);
                    write.putBoolean("SAT" , false);
                    write.putString("S", "");

                }
            }
        });


        cb_sun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    cb_sun.setChecked(true);
                    write.putString("SU", "SU,");
                    write.putBoolean("SUN", true);
                }
                else{
                    cb_sun.setChecked(false);
                    write.putBoolean("SUN" , false);
                    write.putString("SU", "");

                }
            }
        });



        final android.support.v7.app.AlertDialog b = dialogBuilder.create();
        b.show();

        tv_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write.commit();
                tv_text_repeat.setText(sharedPreferences.getString("M", "") +
                        sharedPreferences.getString("TU", "") + " "+
                        sharedPreferences.getString("W", "") + " "+
                        sharedPreferences.getString("TH", "") + " "+
                        sharedPreferences.getString("F", "") + " "+
                        sharedPreferences.getString("S", "") + " "+
                        sharedPreferences.getString("SU", "") + " ");
                b.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();

            }
        });


    }


    boolean pref_mon, pref_tues, pref_wed, pref_thr, pref_fri, pref_sat, pref_sun;
    public void check_uncheck()
    {
        pref_mon = sharedPreferences.getBoolean("MON" ,false);
        pref_tues = sharedPreferences.getBoolean("TUE" ,false);
        pref_wed = sharedPreferences.getBoolean("WED" ,false);
        pref_thr = sharedPreferences.getBoolean("THR" ,false);
        pref_fri = sharedPreferences.getBoolean("FRI" ,false);
        pref_sat = sharedPreferences.getBoolean("SAT" ,false);
        pref_sun = sharedPreferences.getBoolean("SUN" ,false);


        if(pref_mon == true)
        {
            cb_mon.setChecked(true);
        }
        else {
            cb_mon.setChecked(false);
        }



        if(pref_tues == true)
        {
            cb_tue.setChecked(true);
        }
        else {
            cb_tue.setChecked(false);
        }



        if(pref_wed == true)
        {
            cb_wed.setChecked(true);
        }
        else {
            cb_wed.setChecked(false);
        }


        if(pref_thr == true)
        {
            cb_thurs.setChecked(true);
        }
        else {
            cb_thurs.setChecked(false);
        }


        if(pref_fri == true)
        {
            cb_fri.setChecked(true);
        }
        else {
            cb_fri.setChecked(false);
        }


        if(pref_sat == true)
        {
            cb_sat.setChecked(true);
        }
        else {
            cb_sat.setChecked(false);
        }


        if(pref_sun == true)
        {
            cb_sun.setChecked(true);
        }
        else
        {
            cb_sun.setChecked(false);
        }




    }


}
