package com.example.onlinelearningplatform.controllers;

import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Enrollment> enrollmentRequests = enrollmentService.getPendingRequestsForInstructor(instructorId);
        return ResponseEntity.ok(enrollmentRequests);
    }
    @GetMapping("/approved-enrollment-requests/{instructorId}")
    public ResponseEntity<List<Enrollment>> getAcceptedRequestsForInstructor(@PathVariable Long instructorId) {
        List<Enrollment> enrollmentRequests = enrollmentService.getApprovedRequestsForInstructor(instructorId);
        return ResponseEntity.ok(enrollmentRequests);
    }
    @GetMapping("/rejected-enrollment-requests/{instructorId}")
    public ResponseEntity<List<Enrollment>> getRejectedRequestsForInstructor(@PathVariable Long instructorId) {
        List<Enrollment> enrollmentRequests = enrollmentService.getRejectedEnrollmentRequestsForInstructor(instructorId);
        return ResponseEntity.ok(enrollmentRequests);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsForStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/student/{studentId}/accepted")
    public ResponseEntity<List<Enrollment>> getAcceptedEnrollmentsForStudent(@PathVariable Long studentId) {
        List<Enrollment> acceptedEnrollments = enrollmentService.getAcceptedEnrollmentsForStudent(studentId);
        return ResponseEntity.ok(acceptedEnrollments);
    }

    @GetMapping("/student/{studentId}/rejected")
    public ResponseEntity<List<Enrollment>> getRejectedEnrollmentsForStudent(@PathVariable Long studentId) {
        List<Enrollment> rejectedEnrollments = enrollmentService.getRejectedEnrollmentsForStudent(studentId);
        return ResponseEntity.ok(rejectedEnrollments);
    }

    @GetMapping("/student/{studentId}/pending")
    public ResponseEntity<List<Enrollment>> getPendingEnrollmentsForStudent(@PathVariable Long studentId) {
        List<Enrollment> pendingEnrollments = enrollmentService.getPendingEnrollmentsForStudent(studentId);
        return ResponseEntity.ok(pendingEnrollments);
    }

    @DeleteMapping("/{studentId}/{courseId}")
    public ResponseEntity<String> cancelPendingEnrollment(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            enrollmentService.cancelPendingEnrollment(studentId, courseId);
            return ResponseEntity.ok("Enrollment canceled successfully.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
