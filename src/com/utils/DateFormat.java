package com.utils;

public class DateFormat {


    public DateFormat(){
    }


    public String formatDate(String date) {
       String day = date.split("-")[2];
       String month = date.split("-")[1];
       String year = date.split("-")[0];

       //Data formatada (dd-mm-yyyy)
       String f_date = day + "-" + month + "-" + year;

       return f_date;
    }

    public String getYear(String date){
        String year = date.split(" ")[5];

        return year;
    }

}
