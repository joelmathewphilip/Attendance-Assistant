package com.example.attendanceassistant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView user_name,day,period;
    ListView listview;
    DataBase_Helper db;
    ImageButton update_button,refresh_button,time_table_view;
    ArrayList<DataClass_Main_List> obj=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Created the database object.
        db=new DataBase_Helper(this);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        getSupportActionBar().setElevation(0);

        View view = getSupportActionBar().getCustomView();

       SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime", false)) {


            Intent intent=new Intent(this,Starting_staff_details.class);
            startActivity(intent);



           SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("firstTime", true);
            editor.commit();
        }




        //To get the date
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        user_name=(TextView)findViewById(R.id.username_textbox);
        day=(TextView)findViewById(R.id.day_textbox);
        //period=(TextView)findViewById(R.id.period_textbox);
        refresh_button=(ImageButton)findViewById(R.id.refresh);

        day.setText(dayOfTheWeek);

        listview=(ListView)findViewById(R.id.List);

        View view2 = getSupportActionBar().getCustomView();


        listview=(ListView)findViewById(R.id.List);
        Cursor res=db.getAlldata_attendance_table();
        if(res.getCount()==0)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Problem!!");
            builder.setMessage("No Attendance Data Available");
            builder.show();
            Intent intent=new Intent(this,Starting_staff_details.class);
            startActivity(intent);
        }
        else
        {
            ArrayList<DataClass_Main_List> appsList = new ArrayList<>();
            while(res.moveToNext())
            {
                appsList.add(new DataClass_Main_List(res.getString(1),res.getString(2),res.getString(3)));

            }


            AdapterClass_Main_List mAdapter=new AdapterClass_Main_List(getApplicationContext(),appsList);
            listview.setAdapter(mAdapter);
        }



        update_button=(ImageButton)view.findViewById(R.id.imageButton);
        time_table_view=(ImageButton)findViewById(R.id.imageButton2);


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Time_Table.class);
                startActivity(intent);
            }
        });

        time_table_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), AttendanceView.class);
                startActivity(intent);
            }
        });
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = db.getAlldata_attendance_table();
                if (res.getCount() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setCancelable(true);
                    builder.setTitle("Problem!!");
                    builder.setMessage("No Attendance Data Available");
                    builder.show();
                    return;
                }
                else
                    {

                    ArrayList<DataClass_Main_List> appsList = new ArrayList<>();
                    while (res.moveToNext()) {
                        appsList.add(new DataClass_Main_List(res.getString(1), res.getString(2), res.getString(3)));

                    }


                    AdapterClass_Main_List mAdapter = new AdapterClass_Main_List(getApplicationContext(), appsList);
                    listview.setAdapter(mAdapter);
                }
            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Attendance Assistant!")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                       finishAffinity();
                    }
                }).create().show();
    }
}
