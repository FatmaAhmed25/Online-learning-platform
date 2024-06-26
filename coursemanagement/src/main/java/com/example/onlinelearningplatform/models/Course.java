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
    private Integer duration;
    private Double rating=0.0;
    private Integer capacity;
    private int numberOfEnrolledStudents;

    private Long adminId;
    @Enumerated(EnumType.STRING)
    private CourseStatus status = CourseStatus.PENDING;
    @ElementCollection
    private Set<Long> enrolledStudentIds = new HashSet<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<CourseReview> reviews = new HashSet<>();

    public Course() {
        // Initialize enrolledStudentIds if it's null
        if (this.enrolledStudentIds == null) {
            this.enrolledStudentIds = new HashSet<>();
        }
        // Initialize numberOfEnrolledStudents
        this.numberOfEnrolledStudents = this.enrolledStudentIds.size();
    }
}
