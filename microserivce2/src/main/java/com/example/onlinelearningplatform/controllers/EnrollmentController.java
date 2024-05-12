package com.example.onlinelearningplatform.controllers;

import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("enrollments")
public class EnrollmentController {


    @Autowired
    EnrollmentService enrollmentService;

    @PutMapping("/approve/{enrollmentId}")
    public ResponseEntity<String> approveEnrollment(@PathVariable Long enrollmentId)
    {
       return enrollmentService.approveEnrollment(enrollmentId);

    }

    @PutMapping("/reject/{enrollmentId}")
    public ResponseEntity<String> rejectEnrollment(@PathVariable Long enrollmentId) {
        return enrollmentService.rejectEnrollment(enrollmentId);

    }
    @GetMapping("/enrollment-requests/{instructorId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentRequestsForInstructor(@PathVariable Long instructorId) {
        List<Enrollment> enrollmentRequests = enrollmentService.getEnrollmentRequestsForInstructor(instructorId);
        return ResponseEntity.ok(enrollmentRequests);
    }

}
