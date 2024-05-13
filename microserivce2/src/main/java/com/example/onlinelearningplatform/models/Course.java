package com.example.onlinelearningplatform.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="courses")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long instructorId;
    private String name;
    private String category;
    private int duration;
    private double rating;
    private int capacity;
    private boolean status=false;
    private int numberOfEnrolledStudents;

    @ElementCollection
    private Set<Long> enrolledStudentIds = new HashSet<>();

    public Course() {
        // Initialize enrolledStudentIds if it's null
        if (this.enrolledStudentIds == null) {
            this.enrolledStudentIds = new HashSet<>();
        }
        // Initialize numberOfEnrolledStudents
        this.numberOfEnrolledStudents = this.enrolledStudentIds.size();
    }
}
