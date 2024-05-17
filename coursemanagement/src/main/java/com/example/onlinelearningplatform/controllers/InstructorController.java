package com.example.onlinelearningplatform.controllers;



import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/createcourse")
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @RequestParam Long instructorId) {
        return  instructorService.createCourse(course,instructorId);
    }

    @GetMapping("/approvedCourses/{instructorId}")
    public ResponseEntity<Object> getApprovedCoursesForInstructor(@PathVariable Long instructorId) {

        return instructorService.getApprovedCoursesForInstructor(instructorId);
    }
    @GetMapping("/pendingCourses/{instructorId}")
    public ResponseEntity<Object> getPendingCoursesForInstructor(@PathVariable Long instructorId) {

        return instructorService.getPendingCoursesForInstructor(instructorId);
    }
    @GetMapping("/{instructorId}/approved-count")
    public ResponseEntity<Long> countApprovedCoursesByInstructorId(@PathVariable Long instructorId) {
        long count = instructorService.countApprovedCoursesByInstructorId(instructorId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/{instructorId}/pending-count")
    public ResponseEntity<Long> countPendingCoursesByInstructorId(@PathVariable Long instructorId) {
        long count = instructorService.countPendingCoursesByInstructorId(instructorId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }





}
