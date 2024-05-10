package com.example.onlinelearningplatform.entities;


import javax.persistence.*;

@Entity
@Table(name="student")
public class Student extends User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Student() {
        super();
    }
    public Student(String name, String email, String password, String affiliation, String bio, UserRole role) {
        super(name, email, password, affiliation, bio, role);
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
