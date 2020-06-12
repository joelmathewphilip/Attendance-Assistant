package com.example.attendanceassistant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Starting_staff_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_staff_details);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_staff_starting);
        getSupportActionBar().setElevation(0);

        alert_message();


        final EditText p1_code,p1_prof,p2_code,p2_prof,p3_code,p3_prof,p4_code,p4_prof,p5_code,p5_prof,p6_code,p6_prof;
        final Spinner p1_spin,p2_spin,p3_spin,p4_spin,p5_spin,p6_spin;

        ImageButton staff_btn;
        staff_btn=(ImageButton)findViewById(R.id.staff_btn);

        p1_code=(EditText)findViewById(R.id.p1_code);
        p2_code=(EditText)findViewById(R.id.p2_code);
        p3_code=(EditText)findViewById(R.id.p3_code);
        p4_code=(EditText)findViewById(R.id.p4_code);
        p5_code=(EditText)findViewById(R.id.p5_code);
        p6_code=(EditText)findViewById(R.id.p6_code);

        p1_prof=(EditText)findViewById(R.id.p1_prof);
        p2_prof=(EditText)findViewById(R.id.p2_prof);
        p3_prof=(EditText)findViewById(R.id.p3_prof);
        p4_prof=(EditText)findViewById(R.id.p4_prof);
        p5_prof=(EditText)findViewById(R.id.p5_prof);
        p6_prof=(EditText)findViewById(R.id.p6_prof);

        p1_spin=(Spinner)findViewById(R.id.p1_spin);
        p2_spin=(Spinner)findViewById(R.id.p2_spin);
        p3_spin=(Spinner)findViewById(R.id.p3_spin);
        p4_spin=(Spinner)findViewById(R.id.p4_spin);
        p5_spin=(Spinner)findViewById(R.id.p5_spin);
        p6_spin=(Spinner)findViewById(R.id.p6_spin);


        View view=getSupportActionBar().getCustomView();
        ImageButton img=(ImageButton)view.findViewById(R.id.info_btn);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_message();
            }
        });


        final DataBase_Helper db;
        db=new DataBase_Helper(this);

        staff_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int flag=1;
                if (p1_code.getText().toString().equals("") || p2_code.getText().toString().equals("")|| p3_code.getText().toString().equals("")||
                    p4_code.getText().toString().equals("")|| p5_code.getText().toString().equals("")|| p6_code.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter all subject codes", Toast.LENGTH_SHORT).show();
                    flag=0;
                }

                if (p1_prof.getText().toString().equals("") || p2_prof.getText().toString().equals("")|| p3_prof.getText().toString().equals("")||
                        p4_prof.getText().toString().equals("")|| p5_prof.getText().toString().equals("")|| p6_prof.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please enter the names of all Professors", Toast.LENGTH_SHORT).show();
                    flag=0;
                }

                if(p1_code.getText().toString().equals(p2_code.getText().toString()) || p1_code.getText().toString().equals(p3_code.getText().toString())|| (p1_code.getText().toString().equals(p4_code.getText().toString())) ||
                (p1_code.getText().toString().equals(p5_code.getText().toString())) || (p1_code.getText().toString().equals(p6_code.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                    flag=2;
                }
                if(p2_code.getText().toString().equals(p1_code.getText().toString()) || p2_code.getText().toString().equals(p3_code.getText().toString())|| (p2_code.getText().toString().equals(p4_code.getText().toString())) ||
                        (p2_code.getText().toString().equals(p5_code.getText().toString())) || (p2_code.getText().toString().equals(p6_code.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                    flag=2;
                }
                if(p3_code.getText().toString().equals(p2_code.getText().toString()) || p3_code.getText().toString().equals(p1_code.getText().toString())|| (p3_code.getText().toString().equals(p4_code.getText().toString())) ||
                        (p3_code.getText().toString().equals(p5_code.getText().toString())) || (p3_code.getText().toString().equals(p6_code.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                    flag=2;
                }
                if(p4_code.getText().toString().equals(p2_code.getText().toString()) || p4_code.getText().toString().equals(p3_code.getText().toString())|| (p4_code.getText().toString().equals(p1_code.getText().toString())) ||
                        (p4_code.getText().toString().equals(p5_code.getText().toString())) || (p4_code.getText().toString().equals(p6_code.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                    flag=2;
                }
                if(p5_code.getText().toString().equals(p2_code.getText().toString()) || p5_code.getText().toString().equals(p3_code.getText().toString())|| (p5_code.getText().toString().equals(p4_code.getText().toString())) ||
                        (p5_code.getText().toString().equals(p1_code.getText().toString())) || (p5_code.getText().toString().equals(p6_code.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                    flag=2;
                }
                if(p6_code.getText().toString().equals(p2_code.getText().toString()) || p6_code.getText().toString().equals(p3_code.getText().toString())|| (p6_code.getText().toString().equals(p4_code.getText().toString())) ||
                        (p6_code.getText().toString().equals(p5_code.getText().toString())) || (p6_code.getText().toString().equals(p1_code.getText().toString())))
                {

                    flag=2;
                }


                if(p1_prof.getText().toString().equals(p2_prof.getText().toString()) || (p1_prof.getText().toString().equals(p3_prof.getText().toString()))|| (p1_prof.getText().toString().equals(p4_prof.getText().toString())) ||
                        (p1_prof.getText().toString().equals(p5_prof.getText().toString())) || (p1_prof.getText().toString().equals(p6_prof.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                    flag=3;
                }
                if(p2_prof.getText().toString().equals(p1_prof.getText().toString()) || (p2_prof.getText().toString().equals(p3_prof.getText().toString()))|| (p2_prof.getText().toString().equals(p4_prof.getText().toString())) ||
                        (p2_prof.getText().toString().equals(p5_prof.getText().toString())) || (p2_prof.getText().toString().equals(p6_prof.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                    flag=3;
                }
                if(p3_prof.getText().toString().equals(p2_prof.getText().toString()) || (p3_prof.getText().toString().equals(p1_prof.getText().toString()))|| (p3_prof.getText().toString().equals(p4_prof.getText().toString())) ||
                        (p3_prof.getText().toString().equals(p5_prof.getText().toString())) || (p3_prof.getText().toString().equals(p6_prof.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                    flag=3;
                }
                if(p4_prof.getText().toString().equals(p2_prof.getText().toString()) || (p4_prof.getText().toString().equals(p3_prof.getText().toString()))|| (p4_prof.getText().toString().equals(p1_prof.getText().toString())) ||
                        (p4_prof.getText().toString().equals(p5_prof.getText().toString())) || (p4_prof.getText().toString().equals(p6_prof.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                    flag=3;
                }
                if(p5_prof.getText().toString().equals(p2_prof.getText().toString()) || p5_prof.getText().toString().equals(p3_prof.getText().toString())|| (p5_prof.getText().toString().equals(p4_prof.getText().toString())) ||
                        (p5_prof.getText().toString().equals(p1_prof.getText().toString())) || (p5_prof.getText().toString().equals(p6_prof.getText().toString())))
                {

                    flag=3;
                }
                if(p6_prof.getText().toString().equals(p2_prof.getText().toString()) || p6_prof.getText().toString().equals(p3_prof.getText().toString())|| (p6_prof.getText().toString().equals(p4_prof.getText().toString())) ||
                        (p6_prof.getText().toString().equals(p5_prof.getText().toString())) || (p6_prof.getText().toString().equals(p1_prof.getText().toString())))
                {
                    //Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                    flag=3;
                }

                if (flag==2)
                {
                    Toast.makeText(getApplicationContext(),"Please enter unique Course Codes for subjects", Toast.LENGTH_SHORT).show();
                }

                if (flag==3)
                {
                    Toast.makeText(getApplicationContext(),"Please enter unique Course Professors for subjects", Toast.LENGTH_SHORT).show();
                }
                if(flag==1)
                {
                    db.delete_table_staff_list();
                db.insert_data_staff_list(p1_code.getText().toString().trim(),p1_prof.getText().toString().trim(),Integer.valueOf(p1_spin.getSelectedItem().toString()));
                db.insert_data_staff_list(p2_code.getText().toString().trim(),p2_prof.getText().toString().trim(),Integer.valueOf(p2_spin.getSelectedItem().toString()));
                db.insert_data_staff_list(p3_code.getText().toString().trim(),p3_prof.getText().toString().trim(),Integer.valueOf(p3_spin.getSelectedItem().toString()));
                db.insert_data_staff_list(p4_code.getText().toString().trim(),p4_prof.getText().toString().trim(),Integer.valueOf(p4_spin.getSelectedItem().toString()));
                db.insert_data_staff_list(p5_code.getText().toString().trim(),p5_prof.getText().toString().trim(),Integer.valueOf(p5_spin.getSelectedItem().toString()));
                db.insert_data_staff_list(p6_code.getText().toString().trim(),p6_prof.getText().toString().trim(),Integer.valueOf(p6_spin.getSelectedItem().toString()));

                db.delete_table_attendance_table();
                db.insert_data_attendance_table(p1_code.getText().toString().trim(),"Dr."+p1_prof.getText().toString().trim(),0,0);
                db.insert_data_attendance_table(p2_code.getText().toString().trim(),"Dr."+p2_prof.getText().toString().trim(),0,0);
                db.insert_data_attendance_table(p3_code.getText().toString().trim(),"Dr."+p3_prof.getText().toString().trim(),0,0);
                db.insert_data_attendance_table(p4_code.getText().toString().trim(),"Dr."+p4_prof.getText().toString().trim(),0,0);
                db.insert_data_attendance_table(p5_code.getText().toString().trim(),"Dr."+p5_prof.getText().toString().trim(),0,0);
                db.insert_data_attendance_table(p6_code.getText().toString().trim(),"Dr."+p6_prof.getText().toString().trim(),0,0);

                Toast.makeText(getApplicationContext(),"Staff Details Updated Successfully!!", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Time_Table_Starting.class);
                startActivity(intent);

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


    public void alert_message()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Note!!!");
        builder.setMessage("Please Enter The Course and the respective staff details. The Lab component if present should be set as 1. You can enter a maximum of 6 subjects");
        builder.show();
    }
}
