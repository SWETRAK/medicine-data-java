package com.pietrakpasek.medicinedata.exceptions;

public class EmailExistsException extends RuntimeException {
    public EmailExistsException() {
        super("Email has to be unique!");
    }
}
