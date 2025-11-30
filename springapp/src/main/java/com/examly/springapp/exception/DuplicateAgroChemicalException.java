package com.examly.springapp.exception;

/**
 * @author Team AgroVisionaries
 * Custom exception to handle scenarios where AgroChemical exists with same name and brand.
 * 
 * Extends `Exception` to allow u exceptions.
 */
public class DuplicateAgroChemicalException extends Exception {

    public DuplicateAgroChemicalException() {

    }

    /**
     * Constructor for `DuplicateAgroChemicalException`.
     *
     * @param message the exception message describing the error.
     */
    public DuplicateAgroChemicalException(String message) {
        super(message);
    }
}
