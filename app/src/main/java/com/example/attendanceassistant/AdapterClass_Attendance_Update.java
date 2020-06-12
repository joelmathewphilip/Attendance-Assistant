package com.example.attendanceassistant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterClass_Attendance_Update extends ArrayAdapter<DataClassAttendanceUpdate>
{
    Context mContext;
    ArrayList<DataClassAttendanceUpdate> apps=new ArrayList<>();
    public AdapterClass_Attendance_Update(@NonNull Context context, ArrayList<DataClassAttendanceUpdate> data)
    {
        super(context, 0,data);
        mContext=context;
        apps=data;
    }

    public View getView(int position, View convertview, ViewGroup parent)
    {
        View listItem=convertview;
        if(listItem==null)
        {
            listItem= LayoutInflater.from(mContext).inflate(R.layout.time_table_row,null);

        }
        final DataClassAttendanceUpdate current_data=apps.get(position);

        TextView day,p1,p2,p3,p4,evening;
        day=(TextView)listItem.findViewById(R.id.day_list2);
        p1=(TextView)listItem.findViewById(R.id.p1_list2);
        p2=(TextView)listItem.findViewById(R.id.p2_list2);
        p3=(TextView)listItem.findViewById(R.id.p3_list2);
        p4=(TextView)listItem.findViewById(R.id.p4_list2);
        evening=(TextView)listItem.findViewById(R.id.evening_list2);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        if(current_data.getDay().equals(dayOfTheWeek))
        {
            day.setText(current_data.getDay());
            day.setTextColor(Color.parseColor("#ff0000"));
            p1.setText(current_data.getP1());
            p1.setTextColor(Color.parseColor("#ff0000"));
            p2.setText(current_data.getP2());
            p2.setTextColor(Color.parseColor("#ff0000"));
            p3.setText(current_data.getP3());
            p3.setTextColor(Color.parseColor("#ff0000"));
            p4.setText(current_data.getP4());
            p4.setTextColor(Color.parseColor("#ff0000"));
            evening.setText(current_data.getEvening());
            evening.setTextColor(Color.parseColor("#ff0000"));
        }
       else
           {
            day.setText(current_data.getDay());
            p1.setText(current_data.getP1());
            p2.setText(current_data.getP2());
            p3.setText(current_data.getP3());
            p4.setText(current_data.getP4());
            evening.setText(current_data.getEvening());
        }

        return listItem;
    }

}
