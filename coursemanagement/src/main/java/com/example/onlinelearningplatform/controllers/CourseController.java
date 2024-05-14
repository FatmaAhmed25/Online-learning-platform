package com.example.onlinelearningplatform.controllers;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;


    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/search/name")
    public ResponseEntity<List<Course>> searchCoursesByName(@RequestParam String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        List<Course> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search/top-rated")
    public ResponseEntity<List<Course>> getTopRatedCourses() {
        List<Course> courses = courseService.getTopRatedCourses();
        return ResponseEntity.ok(courses);
    }




}
