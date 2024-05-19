package com.example.onlinelearningplatform.DTOs;

import com.example.onlinelearningplatform.entities.UserRole;

public class RegisterInstructorDTO {
    private String name;
    private String email;
    private String password;
    private String affiliation;
    private String bio;
    private int yearsOfExperience;

    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public String getBio() {
        return bio;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }
}
