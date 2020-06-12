package com.example.attendanceassistant;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.Buffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Update_Attendance extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView displaydate,Day;
    Spinner p1,p2,p3,p4,evening,p1_2,p2_2,p3_2,p4_2,evening_2;
    String dayLongName,string1,string2;
    ImageButton b1;
    Button alarmbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__attendance);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_attendance_alarm);
        getSupportActionBar().setElevation(0);

        View view = getSupportActionBar().getCustomView();
        ImageButton back=(ImageButton)view.findViewById(R.id.imageButton4);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /*ImageButton alarm=(ImageButton)view.findViewById(R.id.btn_alarm);
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Update_Notification_Alarm.class);
                startActivity(intent);
            }
        });*/


        final TableRow t5, t6, t7, t8, t9;
        t5 = (TableRow) findViewById(R.id.tableRow5);
        t6 = (TableRow) findViewById(R.id.tableRow6);
        t7 = (TableRow) findViewById(R.id.tableRow7);
        t8 = (TableRow) findViewById(R.id.tableRow8);
        t9 = (TableRow) findViewById(R.id.tableRow9);

        p1 = (Spinner) findViewById(R.id.spinner_p1);
        p2 = (Spinner) findViewById(R.id.spinner_p2);
        p3 = (Spinner) findViewById(R.id.spinner_p3);
        p4 = (Spinner) findViewById(R.id.spinner_p4);
        evening = (Spinner) findViewById(R.id.spinner_p5);
        b1=(ImageButton)findViewById(R.id.Update);


        p1_2=(Spinner)findViewById(R.id.spinner_p1_2);
        p2_2=(Spinner)findViewById(R.id.spinner_p2_2);
        p3_2=(Spinner)findViewById(R.id.spinner_p3_2);
        p4_2=(Spinner)findViewById(R.id.spinner_p4_2);
        evening_2=(Spinner)findViewById(R.id.spinner_p5_2);



        t5.setVisibility(View.INVISIBLE);
        t6.setVisibility(View.INVISIBLE);
        t7.setVisibility(View.INVISIBLE);
        t8.setVisibility(View.INVISIBLE);
        t9.setVisibility(View.INVISIBLE);


        displaydate = (TextView) findViewById(R.id.tvDate2);
        Day = (TextView) findViewById(R.id.Day);
        final Switch s1 = (Switch) findViewById(R.id.switch1);

        displaydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Update_Attendance.this, android.R.style.Theme_Material_Dialog_MinWidth, dateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();


            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                Date date = new Date(year, month, dayOfMonth - 1);
                String dayoftheweek = simpleDateFormat.format(date);

                String date2 = dayOfMonth + "/" + month + "/" + year;
                displaydate.setText(date2);
                Day.setText(dayoftheweek);


                AlertDialog.Builder builder = new AlertDialog.Builder(Update_Attendance.this);
                builder.setCancelable(true);
                builder.setTitle("Reminder!!");
                builder.setMessage("The Month is one less than the Actual.\neg:January is Represented as 0.");
                builder.show();
            }
        };



        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (s1.isChecked()) {

                    t5.setVisibility(View.VISIBLE);
                    t6.setVisibility(View.VISIBLE);
                    t7.setVisibility(View.VISIBLE);
                    t8.setVisibility(View.VISIBLE);
                    t9.setVisibility(View.VISIBLE);




                } else
                    {
                    t5.setVisibility(View.INVISIBLE);
                    t6.setVisibility(View.INVISIBLE);
                    t7.setVisibility(View.INVISIBLE);
                    t8.setVisibility(View.INVISIBLE);
                    t9.setVisibility(View.INVISIBLE);
                }
            }
        });


        DataBase_Helper obj = new DataBase_Helper(this);
        obj.close();

        Cursor res = obj.getAlldata_staff_list();

        if (res.getCount()==0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setTitle("Problem!!");
            builder.setMessage("Some Staff Data Missing");
            builder.show();
            return;
        }
        else
            {

                ArrayList<String> arrayList = new ArrayList<>();
                ArrayList<String> arrayList2=new ArrayList<>();
                ArrayList<String> arrayList3=new ArrayList<>();
                arrayList2.add("No Class");
                arrayList.add("No Class");

                //arrayList3.add("Nil");
                arrayList3.add("Present");
                arrayList3.add("Absent");

                while (res.moveToNext())
                {
                string1 = res.getString(1);
                string2 = res.getString(2);

                arrayList.add("Dr."+string2 );
                if(res.getInt(3)==1)
                 arrayList2.add("Dr."+string2);
                 }


                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList2);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList3);
                arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                p1.setAdapter(arrayAdapter);
                p2.setAdapter(arrayAdapter);
                p3.setAdapter(arrayAdapter);
                p4.setAdapter(arrayAdapter);
                evening.setAdapter(arrayAdapter2);


                p1_2.setAdapter(arrayAdapter3);
                p2_2.setAdapter(arrayAdapter3);
                p3_2.setAdapter(arrayAdapter3);
                p4_2.setAdapter(arrayAdapter3);
                evening_2.setAdapter(arrayAdapter3);
            }


                b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer stringBuffer=new StringBuffer();
                int flag=1;
                String s1,s2,s3,s4,s5,s6,s7,s3_2,s4_2,s5_2,s6_2,s7_2,value;
                s1=displaydate.getText().toString();
                DataBase_Helper object2=new DataBase_Helper(getApplicationContext());
                Cursor res=object2.getAlldata_day_wise_record();
                flag=1;
                if(res.getCount()!=0) {
                    while (res.moveToNext()) {
                        value = res.getString(1);
                        if (value.equals(s1)) {
                            Toast.makeText(getApplicationContext(), "DATA ALREADY INSERTED FOR THE DATE.", Toast.LENGTH_SHORT).show();
                            flag = 0;
                            break;
                        }
                    }
                }
                if(s1.equals("Date"))
                {
                    Toast.makeText(getApplicationContext(),"PLEASE ENTER A VALID DATE",Toast.LENGTH_LONG).show();
                    flag=0;
                }

                    if(flag==1)
                            {
                            s2 = Day.getText().toString();

                            s3 = String.valueOf(p1.getSelectedItem());
                            s4 = String.valueOf(p2.getSelectedItem());
                            s5 = String.valueOf(p3.getSelectedItem());
                            s6 = String.valueOf(p4.getSelectedItem());
                            s7 = String.valueOf(evening.getSelectedItem());

                            s3_2 = String.valueOf(p1_2.getSelectedItem());
                            s4_2 = String.valueOf(p2_2.getSelectedItem());
                            s5_2 = String.valueOf(p3_2.getSelectedItem());
                            s6_2 = String.valueOf(p4_2.getSelectedItem());
                            s7_2 = String.valueOf(evening_2.getSelectedItem());




                            DataBase_Helper db = new DataBase_Helper(getApplicationContext());
                            db.insert_data_day_wise_record(s1, s2, s3 + "(" + s3_2 + ")", s4 + "(" + s4_2 + ")", s5 + "(" + s5_2 + ")", s6 + "(" + s6_2 + ")", s7 + "(" + s7_2 + ")");
                            Toast.makeText(getApplicationContext(), "List Updated", Toast.LENGTH_SHORT).show();


                            DataBase_Helper obj = new DataBase_Helper(getApplicationContext());
                            res = obj.getAlldata_attendance_table();
                            if (res.getCount() == 0) {
                                Toast.makeText(getApplicationContext(), "Attendance not Updated. No Periods", Toast.LENGTH_LONG).show();
                                return;
                            }

                            while (res.moveToNext())
                            {

                                int old_working = res.getInt(4);
                                int old_present = res.getInt(5);
                                String check = res.getString(2);
                                if ((check + "(" + "Absent" + ")").equals(s3 + "(" + s3_2 + ")"))
                                {
                                    old_working = old_working + 1;
                                    //new_present=old_present+1;
                                }

                                  if((check + "(" + "Absent" + ")").equals(s4 + "(" + s4_2 + ")"))
                                    {
                                    old_working = old_working + 1;
                                    }


                                        if ((check + "(" + "Absent" + ")").equals(s5 + "(" + s5_2 + ")"))
                                        {
                                            old_working = old_working + 1;
                                        }

                                       if ((check + "(" + "Absent" + ")").equals(s6 + "(" + s6_2 + ")"))
                                       {
                                           old_working = old_working + 1;
                                       }


                                    if ((check + "(" + "Present" + ")").equals(s3 + "(" + s3_2 + ")"))
                                    {
                                    old_working = old_working + 1;
                                    old_present = old_present + 1;

                                }

                                   if((check + "(" + "Present" + ")").equals(s4 + "(" + s4_2 + ")"))
                                   {
                                       old_working = old_working + 1;
                                       old_present = old_present + 1;
                                   }

                                     if((check + "(" + "Present" + ")").equals(s5 + "(" + s5_2 + ")"))
                                     {
                                         old_working = old_working + 1;
                                         old_present = old_present + 1;
                                     }

                                      if((check + "(" + "Present" + ")").equals(s6 + "(" + s6_2 + ")"))
                                      {
                                          old_working = old_working + 1;
                                          old_present = old_present + 1;
                                      }

                                if ((check + "(" + "Present" + ")").equals(s7 + "(" + s7_2 + ")"))
                                {

                                    old_working = old_working + 3;
                                    old_present = old_present + 3;


                                }
                                else if ((check + "(" + "Absent" + ")").equals(s7 + "(" + s7_2 + ")"))
                                {
                                    old_working = old_working + 3;
                                    //new_present=old_present+3;


                                }
                                obj.update_attendance(res.getString(1), res.getString(2), old_working, old_present);
                            }

                        }



               }

        });





    }

}



