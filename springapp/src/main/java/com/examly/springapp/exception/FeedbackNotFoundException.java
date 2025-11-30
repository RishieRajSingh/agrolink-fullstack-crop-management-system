package com.examly.springapp.exception;

public class FeedbackNotFoundException extends Exception{
    public FeedbackNotFoundException(){

    }
    public FeedbackNotFoundException(String message){
        super(message);
    }
}
