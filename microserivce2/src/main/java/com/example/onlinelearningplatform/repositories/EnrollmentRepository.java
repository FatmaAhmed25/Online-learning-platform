package com.example.onlinelearningplatform.repositories;


import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.models.EnrollmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    // Method to find enrollments by courseId in a set of courseId and status is PENDING
    List<Enrollment> findByCourseIdInAndStatus(Set<Long> courseIds, EnrollmentStatus status);
}
