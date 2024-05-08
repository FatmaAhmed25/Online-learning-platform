package com.example.onlinelearningplatform.entities;


import javax.persistence.*;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String affiliation;
    private String bio;
    private Integer yearsOfExperience; // Only applicable for instructors

    @Enumerated(EnumType.STRING)
    private UserRole role;
    public Long getId() {
        return id;
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

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public UserRole getRole() {
        return role;
    }
}
