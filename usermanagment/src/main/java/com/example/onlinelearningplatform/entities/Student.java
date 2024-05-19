package com.example.onlinelearningplatform.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {

    private String affiliation;
    private String bio;

    public Student() {
        super();
    }

    public Student(String name, String email, String password, String affiliation, String bio, UserRole role) {
        super(name, email, password, role);
        this.affiliation = affiliation;
        this.bio = bio;
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
}
