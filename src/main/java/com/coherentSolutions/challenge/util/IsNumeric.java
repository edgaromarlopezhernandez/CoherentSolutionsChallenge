package com.coherentSolutions.challenge.util;

/**
 * This class is used to validate whether a String is numeric or not. It contains two methods to check signed and
 * unsigned numbers. Very simple, right?
 */
public final class IsNumeric {
    public static boolean verify(String stringToValidate) {
        if (stringToValidate == null || stringToValidate.isEmpty())
            return false;

        int index = 0;
        if(stringToValidate.charAt(0) == '-'){
            if(stringToValidate.length() >1){
                index++;
            }else{
                return false;
            }
        }
        for(; index<stringToValidate.length();index++){
            if(!Character.isDigit(stringToValidate.charAt(index))){
                return false;
            }
        }
        return true;
    }

    public static boolean verifyUnsigned(String stringToValidate) {
        if (stringToValidate == null || stringToValidate.isEmpty())
            return false;

        int index = 0;
        for(; index<stringToValidate.length();index++){
            if(!Character.isDigit(stringToValidate.charAt(index))){
                return false;
            }
        }
        return true;
    }
}