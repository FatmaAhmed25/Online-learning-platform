package com.example.onlinelearningplatform.services;

import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseReview;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseReviewService {

    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  CourseReviewRepository reviewRepository;

    @Autowired
    private ValidationService validationService;

    public ResponseEntity<Object> addReviewToCourse(Long courseId, Long studentId, CourseReview courseReview) {

        if(!validationService.validateStudent(studentId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: "+studentId+" doesn't exist");
        }
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + courseId));

        courseReview.setCreatedAt(new Date());
        courseReview.setCourse(course);
        courseReview.setStudentId(studentId); // Set the student ID

        return ResponseEntity.status(HttpStatus.OK).body(reviewRepository.save(courseReview));
    }
    public List<CourseReview> getReviewsForCourse(Long courseId) {
        return reviewRepository.findByCourseId(courseId);
    }
}
