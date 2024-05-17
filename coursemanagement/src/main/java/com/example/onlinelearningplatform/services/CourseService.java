package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.*;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findByStatus(CourseStatus.APPROVED);
    }



    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findByIdAndStatus(id, CourseStatus.APPROVED);
    }


    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCaseAndStatus(name, CourseStatus.APPROVED);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategoryContainingIgnoreCaseAndStatus(category, CourseStatus.APPROVED);
    }

    public List<Course> getTopRatedCourses() {
        return courseRepository.findByStatusOrderByRatingDesc(CourseStatus.APPROVED);
    }


    public long count() {
        return courseRepository.countByStatus(CourseStatus.APPROVED);
    }
    public List<Course> getAvailableCoursesForStudent(Long studentId) {
        return courseRepository.findAvailableCoursesForStudent(studentId);
    }

}
