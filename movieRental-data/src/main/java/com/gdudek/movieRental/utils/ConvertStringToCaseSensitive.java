package com.gdudek.movieRental.utils;

public class ConvertStringToCaseSensitive {

    public static String getConvertedString(String stringToConvert)
    {
        String convertedString="";
        return convertedString = stringToConvert.substring(0,1).toUpperCase()+stringToConvert.substring(1).toLowerCase();
    }
}
