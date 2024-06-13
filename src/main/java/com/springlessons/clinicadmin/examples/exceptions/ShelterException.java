package com.springlessons.clinicadmin.examples.exceptions;

public class ShelterException extends Exception{
    public ShelterException(String message) {
        super(message);
    }

    public ShelterException(String message, Throwable cause) {
        super(message, cause);
    }
}
