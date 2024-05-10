package com.example.onlinelearningplatform.DTOs;

public class RegisterInstructorDTO {
    private String name;
    private String email;
    private String password;
    private String affiliation;
    private String bio;
    private Integer yearsOfExperience;

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

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }
}
