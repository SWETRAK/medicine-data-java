package com.pietrakpasek.medicinedata.exceptions;

public class NotMatchingPasswordException extends RuntimeException{
    public NotMatchingPasswordException() {
        super("Passwords do not match");
    }
}
