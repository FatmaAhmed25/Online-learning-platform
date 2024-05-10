package com.example.onlinelearningplatform.entities;

import javax.persistence.*;

import javax.persistence.Table;


@Entity
@Table(name ="test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}