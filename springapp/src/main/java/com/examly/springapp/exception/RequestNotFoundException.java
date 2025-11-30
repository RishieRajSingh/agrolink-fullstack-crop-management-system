package com.examly.springapp.exception;

public class RequestNotFoundException extends Exception{
    public RequestNotFoundException(){

    }
    public RequestNotFoundException(String message){
        super(message);
    }
}
