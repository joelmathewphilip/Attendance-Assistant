package com.example.attendanceassistant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Time_Table extends AppCompatActivity {


    DataBase_Helper obj=new DataBase_Helper(this);
    ListView listview2;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_timetable);
        getSupportActionBar().setElevation(0);

        View view=this.getSupportActionBar().getCustomView();

        ImageButton imageButton=(ImageButton) view.findViewById(R.id.imageButton3);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        View view2=getSupportActionBar().getCustomView();
        imageView=view2.findViewById(R.id.timetable_image_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Update_Attendance.class);
                startActivity(intent);
            }
        });



        listview2=(ListView)findViewById(R.id.listview2);

        Cursor res=obj.getAlldata_time_table();
        if(res.getCount()==0)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Problem!!");
            builder.setMessage("No Time Table Data Displayed");
            builder.show();
            return;
        }
        else
        {
            ArrayList<DataClassAttendanceUpdate> appsList = new ArrayList<>();
            while(res.moveToNext())
            {
                appsList.add(new DataClassAttendanceUpdate(res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6)));

            }


            AdapterClass_Attendance_Update mAdapter=new AdapterClass_Attendance_Update(getApplicationContext(),appsList);
            listview2.setAdapter(mAdapter);
        }




    }


}
