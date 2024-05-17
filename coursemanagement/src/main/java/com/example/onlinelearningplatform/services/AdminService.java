package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseStatus;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private ValidationService validationService;

    public List<Course> getPendingCourses() {
        return courseRepository.findByStatus(CourseStatus.PENDING);
    }
    public List<Course> getAcceptedCourses() {
        return courseRepository.findByStatus(CourseStatus.APPROVED);
    }
    public List<Course> getRejectedCourses() {
        return courseRepository.findByStatus(CourseStatus.REJECTED);
    }

    public ResponseEntity<Object> approveCourse(Long courseId, Long adminId) {
        if(!validationService.validateAdmin(adminId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID: "+adminId+" doesn't exist");
        }
        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            if (course.getStatus().equals(CourseStatus.PENDING))
            {
                course.setStatus(CourseStatus.APPROVED);
                course.setAdminId(adminId);

                return ResponseEntity.status(HttpStatus.OK).body(courseRepository.save(course));
            }
            else
            {

                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Course : " + courseId
                                + "is Already approved");
            }
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID: "+courseId+" doesn't exist");
        }
    }

    public ResponseEntity<Object> rejectCourse(Long courseId,Long adminId) {

        if(validationService.validateAdmin(adminId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID: "+adminId+" doesn't exist");
        }

        Optional<Course> optionalCourse = courseRepository.findById(courseId);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            if (course.getStatus().equals(CourseStatus.PENDING)) {
                course.setStatus(CourseStatus.REJECTED);
                courseRepository.save(course);
                return ResponseEntity.ok().body("Course rejected successfully");
            }
            else {

                return ResponseEntity.badRequest().body("Course is already accepted");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID: "+courseId+" doesn't exist");
        }
    }
}
