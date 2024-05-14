package com.example.onlinelearningplatform.controllers;

import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/approve/{courseId}")
    public ResponseEntity<Object> approveCourse(@PathVariable Long courseId, @RequestParam Long adminId) {
        return  adminService.approveCourse(courseId, adminId);

    }
    @PostMapping("/reject/{courseId}")
    public ResponseEntity<Object> rejectCourse(@PathVariable Long courseId, @RequestParam Long adminId) {
        return adminService.rejectCourse(courseId, adminId);

    }

    @GetMapping("/pending")
    public ResponseEntity<List<Course>> getCoursesPending() {
        List<Course> pendingCourses = adminService.getPendingCourses();
        return ResponseEntity.ok(pendingCourses);
    }



}
