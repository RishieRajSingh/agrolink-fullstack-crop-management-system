package com.examly.springapp.exception;

public class CropNotFoundException extends Exception{
    public CropNotFoundException(){

    }
    public CropNotFoundException(String message){
        super(message);
    }
}
