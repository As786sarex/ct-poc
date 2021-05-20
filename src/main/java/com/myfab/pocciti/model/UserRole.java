package com.myfab.pocciti.model;

public enum UserRole {
    STUDENT("student"),
    TEACHER("teacher"),
    ADMIN("admin");

    private final String text;

    UserRole(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
