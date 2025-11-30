package com.examly.springapp.exception;

public class AgroChemicalNotFoundException extends Exception {
    public AgroChemicalNotFoundException(){

    }
    public AgroChemicalNotFoundException(String message){
        super(message);
    }
}
