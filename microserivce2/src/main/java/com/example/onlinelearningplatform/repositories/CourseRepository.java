package com.example.onlinelearningplatform.repositories;


import com.example.onlinelearningplatform.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository <Course,Long> {

    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByCategoryContainingIgnoreCase(String category);
    List<Course> findByOrderByRatingDesc();

    List<Course> findByInstructorId(Long instructorId);
}
