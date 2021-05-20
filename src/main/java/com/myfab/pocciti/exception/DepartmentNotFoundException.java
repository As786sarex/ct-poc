package com.myfab.pocciti.exception;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException() {
        this("Department does not exist!");
    }

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
