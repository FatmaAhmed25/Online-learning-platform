package com.example.onlinelearningplatform.services;

import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseReview;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.CourseReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseReviewService {

    @Autowired
    private  CourseRepository courseRepository;
    @Autowired
    private  CourseReviewRepository reviewRepository;

    public void addReviewToCourse(Long courseId, CourseReview courseReview) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + courseId));

        courseReview.setCreatedAt(new Date());
        courseReview.setCourse(course);

        reviewRepository.save(courseReview);
    }
    public List<CourseReview> getReviewsForCourse(Long courseId) {
        return reviewRepository.findByCourseId(courseId);
    }
}
