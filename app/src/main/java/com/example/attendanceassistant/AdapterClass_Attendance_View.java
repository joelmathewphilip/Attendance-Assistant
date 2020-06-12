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

public class AdapterClass_Attendance_View extends ArrayAdapter<DataClass_Attendance_View> {

ArrayList<DataClass_Attendance_View> apps=new ArrayList<>();
Context mContext;
    public AdapterClass_Attendance_View(@NonNull Context context, ArrayList<DataClass_Attendance_View> obj)
    {
        super(context, 0,obj);
        this.apps=obj;
        this.mContext=context;

    }

    public View getView(int position, View convertview, ViewGroup parent)
    {
        View listItem=convertview;
        if(listItem==null)
        {
            listItem= LayoutInflater.from(mContext).inflate(R.layout.attendance_view_row,null);

        }
        final DataClass_Attendance_View current_data=apps.get(position);

        TextView day,p1,p2,p3,p4,evening,date;
        date=(TextView)listItem.findViewById(R.id.view_date);
        day=(TextView)listItem.findViewById(R.id.view_day);
        p1=(TextView)listItem.findViewById(R.id.view_p1);
        p2=(TextView)listItem.findViewById(R.id.view_p2);
        p3=(TextView)listItem.findViewById(R.id.view_p3);
        p4=(TextView)listItem.findViewById(R.id.view_p4);
        evening=(TextView)listItem.findViewById(R.id.view_evening);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String dayOfTheWeek = sdf.format(d);

        if(current_data.getDay().equals(dayOfTheWeek))
        {
            day.setText(current_data.getDay());
            day.setTextColor(Color.parseColor("#ff0000"));
            date.setText(current_data.getDate());
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
            date.setText(current_data.getDate());
            p1.setText(current_data.getP1());
            p2.setText(current_data.getP2());
            p3.setText(current_data.getP3());
            p4.setText(current_data.getP4());
            evening.setText(current_data.getEvening());
        }

        return listItem;
    }

}
