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

@Service
public class InstructorService {


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ValidationService validationService;
    
    public ResponseEntity<Object> createCourse(Course course, Long instructorId) {
        if(!validationService.validateInstructor(instructorId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor with ID: "+instructorId+" doesn't exist");
        }
        course.setInstructorId(instructorId);
        Course savedCourse = courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.OK).body(savedCourse);

    }
    public ResponseEntity<Object> getApprovedCoursesForInstructor(Long instructorId) {
        if(!validationService.validateInstructor(instructorId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor with ID: "+instructorId+" doesn't exist");
        }
        List<Course> courses = courseRepository.findByInstructorIdAndStatus(instructorId,CourseStatus.APPROVED);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }

    public ResponseEntity<Object> getPendingCoursesForInstructor(Long instructorId) {
        if(!validationService.validateInstructor(instructorId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor with ID: "+instructorId+" doesn't exist");
        }
        List<Course> courses = courseRepository.findByInstructorIdAndStatus(instructorId,CourseStatus.PENDING);
        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.status(HttpStatus.OK).body(courses);
    }
    public long countApprovedCoursesByInstructorId(Long instructorId) {
        return courseRepository.countByInstructorIdAndStatus(instructorId, CourseStatus.APPROVED);
    }
    public long countPendingCoursesByInstructorId(Long instructorId) {
        return courseRepository.countByInstructorIdAndStatus(instructorId, CourseStatus.PENDING);
    }
}
