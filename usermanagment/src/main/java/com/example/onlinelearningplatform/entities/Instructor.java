package com.example.onlinelearningplatform.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("INSTRUCTOR")
public class Instructor extends User {

    private String affiliation;
    private String bio;
    private Integer yearsOfExperience;

    public Instructor() {
        super();
    }

    public Instructor(String name, String email, String password, String affiliation, String bio, int yearsOfExperience, UserRole role) {
        super(name, email, password, role);
        this.affiliation = affiliation;
        this.bio = bio;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters and setters

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
}
