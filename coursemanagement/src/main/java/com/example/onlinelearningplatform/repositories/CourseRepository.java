package com.example.onlinelearningplatform.repositories;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository <Course,Long> {
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByCategoryContainingIgnoreCase(String category);
    List<Course> findByOrderByRatingDesc();

    List<Course> findByInstructorId(Long instructorId);

    List<Course> findByInstructorIdAndStatus(Long instructorId,CourseStatus status);
    long countByInstructorIdAndStatus(Long instructorId, CourseStatus status);

    List<Course> findByEnrolledStudentIdsContains(Long studentId);

    List<Course> findByStatus(CourseStatus courseStatus);

    Optional<Course> findByIdAndStatus(Long id, CourseStatus status);

    List<Course> findByNameContainingIgnoreCaseAndStatus(String name, CourseStatus status);

    List<Course> findByCategoryContainingIgnoreCaseAndStatus(String category, CourseStatus status);

    List<Course> findByStatusOrderByRatingDesc(CourseStatus status);

    List<Course> findByEnrolledStudentIdsContainsAndStatus(Long studentId, CourseStatus status);
    boolean existsByIdAndEnrolledStudentIdsIsContaining(Long courseId, Long studentId);
    long countByStatus(CourseStatus status);
    List<Course> findCoursesByStatus(CourseStatus status);
    @Query(value = "SELECT c.* FROM courses c " +
            "LEFT JOIN course_enrolled_student_ids ce ON c.id = ce.course_id " +
            "WHERE ce.enrolled_student_ids <> :studentId OR ce.enrolled_student_ids IS NULL", nativeQuery = true)
    List<Course> findAvailableCoursesForStudent(@Param("studentId") Long studentId);
    List<Course> findByNameContainingIgnoreCaseAndStatusOrderByRatingDesc(String name,CourseStatus status);
    List<Course> findByCategoryContainingIgnoreCaseAndStatusOrderByRatingDesc(String name,CourseStatus status);
}

