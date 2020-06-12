package com.example.attendanceassistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class Update_Notification_Alarm extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView status;
    ImageButton pick_time,cancel_alarm;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__notification__alarm);
        status=(TextView)findViewById(R.id.alarm_status);
        pick_time=(ImageButton)findViewById(R.id.set_time);
        cancel_alarm=(ImageButton)findViewById(R.id.cancel_alarm);
        textView=(TextView)findViewById(R.id.text_notify);
        textView.setVisibility(View.INVISIBLE);


        pick_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment=new TimePickerFragment();
                dialogFragment.show(getSupportFragmentManager(),"TIME PICKER");

            }
        });

        cancel_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel_alarm();
                textView.setVisibility(View.INVISIBLE);
            }
        });

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        calendar.set(Calendar.MINUTE,minute);
        calendar.set(Calendar.SECOND,0);

        updateTimeText(calendar);
        startAlarm(calendar);
        textView.setVisibility(View.VISIBLE);

    }

    public void updateTimeText(Calendar calendar)
    {
        String time;
        time=DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
        DataBase_Helper db=new DataBase_Helper(this);
        db.set_alarm(time);
        status.setText(time);
    }

    public void startAlarm(Calendar calendar)
    {
        /*AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        */
    }
    public void cancel_alarm()
    {
        /*AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(this,AlertReceiver.class);
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,1,intent,0);

        alarmManager.cancel(pendingIntent);
        status.setText("The Alarm is not set!!!");
    */
    }

}
