package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
}
