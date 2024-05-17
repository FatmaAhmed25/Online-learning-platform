package com.example.onlinelearningplatform.controllers;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    StudentService studentService;

    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<?> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        return studentService.enrollStudent(studentId, courseId);
    }
    @GetMapping("/enrolledCourses/{studentId}")
    public ResponseEntity<List<Course>> getEnrolledCoursesForStudent(@PathVariable Long studentId) {
        List<Course> enrolledCourses = studentService.getEnrolledCoursesForStudent(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }
    @GetMapping("/getAvailableCourses/{studentId}")
    public ResponseEntity<List<Course>> getAvailableCourses(@PathVariable Long studentId) {
        List<Course> availableCourses = studentService.getAvailableCoursesForStudent(studentId);
        return ResponseEntity.ok(availableCourses);
    }

}
