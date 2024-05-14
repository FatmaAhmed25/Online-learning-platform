package com.example.onlinelearningplatform.customExceptions;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
        super("Student not found");
    }
}
