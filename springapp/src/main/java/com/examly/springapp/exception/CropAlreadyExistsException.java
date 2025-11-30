package com.examly.springapp.exception;

/**
 * @author Team AgroVisionaries
 * Custom exception to handle scenarios where AgroChemical not found with the cropId.
 * 
 * Extends `Exception` to allow u exceptions.
 */
public class CropAlreadyExistsException extends Exception{

    public CropAlreadyExistsException() {

    }
     /**
     * Constructor for `DuplicateAgroChemicalException`.
     *
     * @param message the exception message describing the error.
     */
    public CropAlreadyExistsException(String message) {
        super(message);
    }
}
