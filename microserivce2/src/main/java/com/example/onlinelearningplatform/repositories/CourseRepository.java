package com.example.onlinelearningplatform.repositories;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course,Long> {

    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByCategoryContainingIgnoreCase(String category);
    List<Course> findByOrderByRatingDesc();

    List<Course> findByInstructorId(Long instructorId);
    List<Course> findByEnrolledStudentIdsContains(Long studentId);

    List<Course> findByStatus(CourseStatus courseStatus);


    Optional<Course> findByIdAndStatus(Long id, CourseStatus status);

    List<Course> findByNameContainingIgnoreCaseAndStatus(String name, CourseStatus status);

    List<Course> findByCategoryContainingIgnoreCaseAndStatus(String category, CourseStatus status);

    //List<Course> findByOrderByRatingDescAndStatus(CourseStatus status);

    List<Course> findByEnrolledStudentIdsContainsAndStatus(Long studentId, CourseStatus status);;
}

