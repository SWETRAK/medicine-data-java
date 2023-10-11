package com.pietrakpasek.medicinedata.exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(String name) {
        super("Role " + name + " does not exist");
    }
}
