package com.example.attendanceassistant;

public class DataClass_Main_List
{
   String subject,prof,percent;
    public DataClass_Main_List (String sub_code,String professor,String percentage)
   {
       this.subject=sub_code;
       this.prof=professor;
       this.percent=percentage;

   }

   public String getSubject()
   {
       return subject;
   }

   public String getProf()
   {
       return prof;
   }

   public String getPercent()
   {
       return percent;
   }

}
