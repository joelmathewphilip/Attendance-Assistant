package com.example.attendanceassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class AttendanceView extends AppCompatActivity {

    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_view);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_attendance_log);
        getSupportActionBar().setElevation(0);

        View view = getSupportActionBar().getCustomView();

        ImageButton back=(ImageButton)view.findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        ListView listView=(ListView)findViewById(R.id.view_listview);


        //spn=(Spinner)findViewById(R.id.spinner_view);
        //ArrayList<String> ars=new ArrayList<>();

        DataBase_Helper db=new DataBase_Helper(this);
        Cursor res=db.getAlldata_day_wise_record();
        if(res.getCount()==0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceView.this);
            builder.setCancelable(true);
            builder.setTitle("Warning!!");
            builder.setMessage("No record updated.Please do.\n\nThe Month is one less than the Actual.\neg:January is Represented as 0.");
            builder.show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(AttendanceView.this);
        builder.setCancelable(true);
        builder.setTitle("Warning!!");
        builder.setMessage("The Month is one less than the Actual.\n eg:January is Represented as 0.");
        builder.show();
       ArrayList<DataClass_Attendance_View> object2=new ArrayList<>();
        while(res.moveToNext())
        {
            object2.add(new DataClass_Attendance_View(res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(1),res.getString(2)));
        }
        AdapterClass_Attendance_View myadapter=new AdapterClass_Attendance_View(this,object2);
        listView.setAdapter(myadapter);

    }

    public void back_button(View view)
    {

    }
}
