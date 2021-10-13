package com.gdudek.movieRental.utils;

public class ConvertStringToCaseSensitive {

    public static String getConvertedString(String stringToConvert)
    {
        String convertedString="";

        String[] splitted = stringToConvert.split(" ");

        for(int i =0 ; i< splitted.length;i++){
            convertedString+=splitted[i].substring(0,1).toUpperCase()+splitted[i].substring(1).toLowerCase()+" ";
        }

        return convertedString;
    }
}
