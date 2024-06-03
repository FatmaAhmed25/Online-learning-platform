package com.example.onlinelearningplatform.repositories;

import com.example.onlinelearningplatform.models.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview,Long> {

    List<CourseReview> findByCourseId(Long courseId);

    List<CourseReview> findByCourseIdAndStudentId(Long courseId, Long studentId);
    // Method to check if a student has already reviewed a course with a rating
    CourseReview findByCourseIdAndStudentIdAndRatingNotNull(Long courseId, Long studentId);

    List<CourseReview> findByCourseIdAndRatingNotNull(Long courseId);
}
