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

//    @GetMapping("")
//    public String show(){
//        return "index";
//    }

    @Autowired
    private CourseService courseService;

    @GetMapping("/pending")
    public ResponseEntity<List<Course>> getCoursesPendingReview() {
        List<Course> pendingCourses = courseService.getPendingCourses();
        return ResponseEntity.ok(pendingCourses);
    }

    @PostMapping("/approve/{courseId}")
    public ResponseEntity<Course> approveCourse(@PathVariable Long courseId, @RequestParam Long adminId) {
        Course approvedCourse = courseService.approveCourse(courseId, adminId);
        if (approvedCourse != null) {
            return ResponseEntity.ok().body(approvedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/reject/{courseId}")
    public ResponseEntity<String> rejectCourse(@PathVariable Long courseId) {
        boolean isRejected = courseService.rejectCourse(courseId);
        if (isRejected) {
            return ResponseEntity.ok().body("Course rejected successfully");
        } else {
            return ResponseEntity.badRequest().body("Course cannot be rejected");
        }
    }
    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCoursesByName(@RequestParam String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        List<Course> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/top-rated")
    public ResponseEntity<List<Course>> getTopRatedCourses() {
        List<Course> courses = courseService.getTopRatedCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course, @RequestParam Long instructorId) {
        Course createdCourse = courseService.createCourse(course, instructorId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }
    @PostMapping("/enroll/{studentId}/{courseId}")
    public ResponseEntity<?> enrollStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
        return courseService.enrollStudent(studentId, courseId);
    }
    @GetMapping("/enrolledcourses/{studentId}")
    public ResponseEntity<List<Course>> getEnrolledCoursesForStudent(@PathVariable Long studentId) {
        List<Course> enrolledCourses = courseService.getEnrolledCoursesForStudent(studentId);
        return ResponseEntity.ok(enrolledCourses);
    }


}
