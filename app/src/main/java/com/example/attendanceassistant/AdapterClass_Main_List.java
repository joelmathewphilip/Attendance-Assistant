package com.example.attendanceassistant;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass_Main_List extends ArrayAdapter<DataClass_Main_List>
{

    private Context mContext;
    private List<DataClass_Main_List> data=new ArrayList<>();

    public AdapterClass_Main_List(@NonNull Context context, ArrayList<DataClass_Main_List> apps)
    {
        super(context,0, apps);
        mContext=context;
        data=apps;

    }


    public View getView(int position, View convertview, ViewGroup parent)
    {
        View listItem=convertview;
        if(listItem==null)
        {
            listItem= LayoutInflater.from(mContext).inflate(R.layout.attendance_main_listview_row,null);

        }
      DataClass_Main_List current_data=data.get(position);

        TextView sub_code,prof,percen;
        sub_code=(TextView)listItem.findViewById(R.id.subjectcode_list);
        prof=(TextView)listItem.findViewById(R.id.professor_list);
        percen=(TextView)listItem.findViewById(R.id.attendance_list);

        if(Float.valueOf(current_data.getPercent())<80)
        {
            sub_code.setText(current_data.getSubject());
            sub_code.setTextColor(Color.parseColor("#ff0000"));
            prof.setText(current_data.getProf());
            prof.setTextColor(Color.parseColor("#ff0000"));
            percen.setText(String.valueOf(current_data.getPercent()));
            percen.setTextColor(Color.parseColor("#ff0000"));
        }
        else
        {
            sub_code.setText(current_data.getSubject());
            sub_code.setTextColor(Color.parseColor("#006400"));
            prof.setText(current_data.getProf());
            prof.setTextColor(Color.parseColor("#006400"));
            percen.setText(String.valueOf(current_data.getPercent()));
            percen.setTextColor(Color.parseColor("#006400"));
        }
        return listItem;
    }


}
