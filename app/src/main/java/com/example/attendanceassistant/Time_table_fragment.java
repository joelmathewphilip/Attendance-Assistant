package com.example.attendanceassistant;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Time_table_fragment extends Fragment {

    DataBase_Helper db;
    public Time_table_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_time_table_fragment, container, false);
        final Spinner sp1, sp2, sp3, sp4, sp5;
        ImageButton imageButton=(ImageButton)v.findViewById(R.id.submit_btn);

        sp1 = (Spinner) v.findViewById(R.id.spin_1);
        sp2 = (Spinner) v.findViewById(R.id.spin_2);
        sp3 = (Spinner) v.findViewById(R.id.spin_3);
        sp4 = (Spinner) v.findViewById(R.id.spin_4);
        sp5 = (Spinner) v.findViewById(R.id.spin_5);


        db = new DataBase_Helper(getContext());
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("Nil");

        ArrayList<String> arrayList2=new ArrayList<>();
        arrayList2.add("Nil");



        Cursor res = db.getAlldata_staff_list();
        if (res.getCount() == 0)
        {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Problem!!");
            builder.setMessage("Please Enter Staff Data");
            builder.show();
            Intent intent=new Intent(getContext(),Starting_staff_details.class);
            startActivity(intent);
        }
        else
        {
            while(res.moveToNext())
            {
                arrayList.add(res.getString(2));
                if (res.getInt(3)==1)
                {
                    arrayList2.add("Lab "+res.getString(2));
                }

            }
        }



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item, arrayList2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(arrayAdapter);
        sp2.setAdapter(arrayAdapter);
        sp3.setAdapter(arrayAdapter);
        sp4.setAdapter(arrayAdapter);
        sp5.setAdapter(arrayAdapter2);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String day=getArguments().getString("message");
                int flag=0;
                int rows=0;
                Cursor res=db.getAlldata_time_table();
                if (res.getCount()!=0)
                {
                    while(res.moveToNext())
                    {

                        if (res.getString(1).equals(day))
                        {
                            Toast.makeText(getContext(),day+"Time Table Already Inserted", Toast.LENGTH_SHORT).show();
                            flag=1;
                        }
                    }
                }
                if (flag==0)
                {
                    db.insert_data_time_table(day, sp1.getSelectedItem().toString(), sp2.getSelectedItem().toString(), sp3.getSelectedItem().toString(), sp4.getSelectedItem().toString(), sp5.getSelectedItem().toString());
                    Toast.makeText(getContext(), day + " TimeTable Inserted", Toast.LENGTH_SHORT).show();
                }

                res=db.getAlldata_time_table();
                if (res.getCount()!=0)
                {
                    while (res.moveToNext())
                    {
                        rows=rows+1;
                        if (rows == 7)
                        {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        return v;
    }



}
