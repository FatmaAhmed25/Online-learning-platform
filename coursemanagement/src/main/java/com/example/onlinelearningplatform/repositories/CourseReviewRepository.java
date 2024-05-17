package com.example.onlinelearningplatform.repositories;

import com.example.onlinelearningplatform.models.CourseReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReview,Long> {

    List<CourseReview> findByCourseId(Long courseId);
}