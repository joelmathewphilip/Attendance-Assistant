package com.example.attendanceassistant;

import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.http.X509TrustManagerExtensions;
import android.os.strictmode.SqliteObjectLeakedViolation;

import androidx.annotation.Nullable;

public class DataBase_Helper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="College.db";
    public static final String attendance_table="attendance_table";
    public static final String subjects_table="subjects_table";
    public static String TABLE_NAME=null;
    public static final String time_table="time_table";
    public static final String present_record="present_record";
    public static final String day_wise_record="day_wise_record";
    public static final String staff_list="staff_list";
    public static final String alarm_manager="alarm_manager";
    public static final String time="time";

    public DataBase_Helper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table "+attendance_table+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, courseid TEXT, profname TEXT, percentage REAL,workingdays INTEGER, presentdays INTEGER )");
        db.execSQL("create table "+subjects_table+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, courseid TEXT, coursename TEXT, profname TEXT )");
        db.execSQL("create table "+time_table+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, day TEXT, p1 TEXT, p2 TEXT, p3 TEXT, p4 TEXT, Evening TEXT )");
        db.execSQL("create table "+present_record+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, coursecode TEXT, profname TEXT, totalworking INTEGER, present INTEGER )");
        db.execSQL("create table "+day_wise_record+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT, Day TEXT, p1 TEXT,p2 TEXT,p3 TEXT, p4 TEXT, Evening TEXT )");
        db.execSQL("create table "+staff_list+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, courseid TEXT, name TEXT,lab INTEGER )");
        db.execSQL("create table "+alarm_manager+"(Id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT)");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+attendance_table);
        db.execSQL("drop table if exists "+subjects_table);
        onCreate(db);

    }

    public boolean insert_data_attendance_table(String courseid,String profname,int workingdays, int presentdays)
    {
        String col2="courseid",col3="profname",col4=" percentage",col5="workingdays",col6="presentdays";
        float final_data=0;
        if(workingdays==0)
        {
            final_data=0;
        }
        else
            {
            final_data = (float) presentdays / workingdays;
            }

        final_data=(float)final_data*100;
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("delete from time_table");
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,courseid);
        contentValues.put(col3,profname);
        contentValues.put(col4,final_data);
        contentValues.put(col5,workingdays);
        contentValues.put(col6,presentdays);
        long result=db.insert(attendance_table,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }


    public void insert_data_time_table(String day,String p1,String p2,String p3,String p4,String evening)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("insert into time_table (day,p1,p2,p3,p4,Evening)values ('"+day+"','"+p1+"','"+p2+"','"+p3+"','"+p4+"','"+evening+"')");
    }



    public boolean insert_data_subjects_table(String courseid,String profname,String coursename)
    {
        String col2="courseid",col4="profname",col3="coursename";
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,courseid);
        contentValues.put(col3,coursename);
        contentValues.put(col4,profname);
        long result=db.insert(subjects_table,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }

    public boolean insert_data_present_record(String coursecode,String profname,int totalworking, int present)
    {
        String col2="coursecode",col3="profname",col4="totalworking",col5="present";
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,coursecode);
        contentValues.put(col3,profname);
        contentValues.put(col4,totalworking);
        contentValues.put(col5,present);
        long result=db.insert(present_record,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }


    public boolean insert_data_day_wise_record(String Date,String Day,String p1, String p2, String p3, String p4,String evening )
    {
        String col2="Date",col3="Day",col4="p1",col5="p2",col6="p3",col7="p4",col8="Evening";
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,Date);
        contentValues.put(col3,Day);
        contentValues.put(col4,p1);
        contentValues.put(col5,p2);
        contentValues.put(col6,p3);
        contentValues.put(col7,p4);
        contentValues.put(col8,evening);

        long result=db.insert(day_wise_record,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }



    public Cursor getAlldata_time_table()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+time_table,null);
        return res;
    }


    public Cursor getAlldata_attendance_table()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+attendance_table,null);
        return res;
    }

    public boolean insert_data_staff_list(String coursecode,String name,int lab)
    {
        String col2="courseid",col3="name",col4="lab";
        SQLiteDatabase db=this.getWritableDatabase();
        //db.execSQL("delete from staff_list");
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,coursecode);
        contentValues.put(col3,name);
        contentValues.put(col4,lab);
        long result=db.insert(staff_list,null,contentValues);
        if (result==-1)
            return false;
        else
            return true;

    }

    public Cursor getAlldata_staff_list()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("Select * from "+staff_list,null);
        return res;
    }

    public Cursor getAlldata_day_wise_record()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor res=sqLiteDatabase.rawQuery("Select * from "+day_wise_record,null);
        return res;
    }

    public boolean update_attendance(String courseid,String profname,int workingdays,int presentdays)
    {
        String col2="courseid",col3="profname",col4="percentage",col5="workingdays",col6="presentdays";
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        float percentage;
        if (workingdays==0)
        {
            percentage=0;
        }
        else
            {
            percentage = (float) presentdays / workingdays;
            percentage = (float) percentage * 100;
        }
        ContentValues contentValues=new ContentValues();
        contentValues.put(col2,courseid);
        contentValues.put(col3,profname);
        contentValues.put(col4,percentage);
        contentValues.put(col5,workingdays);
        contentValues.put(col6,presentdays);
        sqLiteDatabase.update(attendance_table,contentValues,"profname=?",new String[]{profname});
        return true;

    }

    public void set_alarm(String time)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String col1="time";
        ContentValues content=new ContentValues();
        content.put(col1,time);
        sqLiteDatabase.insert(alarm_manager,null,content);

    }

    public Cursor get_Alarm()
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.rawQuery("delete from "+alarm_manager,null);
        Cursor res=sqLiteDatabase.rawQuery("Select * from "+alarm_manager,null);
        return res;
    }

    public void delete_table_staff_list( )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from staff_list");
    }
    public void delete_table_attendance_table( )
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from attendance_table");
    }

}
