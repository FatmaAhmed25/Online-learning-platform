package com.example.onlinelearningplatform.controllers;

import com.example.onlinelearningplatform.models.CourseReview;
import com.example.onlinelearningplatform.services.CourseReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class CourseReviewController {

    @Autowired
    private CourseReviewService reviewService;

    @PostMapping("/courses/{courseId}/students/{studentId}")
    public ResponseEntity<Object> addReviewToCourse(@PathVariable Long courseId,
                                                    @PathVariable Long studentId,
                                                    @RequestBody CourseReview courseReview) {
        reviewService.addReviewToCourse(courseId, studentId, courseReview);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review added successfully");
    }

    @GetMapping("/courses/{courseId}")
    public ResponseEntity<List<CourseReview>> getReviewsForCourse(@PathVariable Long courseId) {
        List<CourseReview> reviews = reviewService.getReviewsForCourse(courseId);
        return ResponseEntity.ok(reviews);
    }
}
