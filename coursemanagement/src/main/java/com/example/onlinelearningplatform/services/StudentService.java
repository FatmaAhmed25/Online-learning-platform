package com.example.onlinelearningplatform.services;

import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.CourseStatus;
import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.models.EnrollmentStatus;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private ValidationService validationService;

    public ResponseEntity<String> enrollStudent(Long studentId, Long courseId) {
        if(!validationService.validateStudent(studentId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: "+studentId+" doesn't exist");
        }
        // Check if the student is already enrolled in the course
        boolean isAlreadyEnrolled = courseRepository.existsByIdAndEnrolledStudentIdsIsContaining(courseId,studentId);

        if (isAlreadyEnrolled) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with ID: " + studentId + " is already enrolled in course with ID: " + courseId);
        }
        //if there is already an enrollment request with the same courseId and studentId
        Optional<Enrollment> existingRequest = enrollmentRepository
                .findByStudentIdAndCourseId(studentId, courseId);

        if (existingRequest.isPresent()) {
            // If there is an existing enrollment request
            if (existingRequest.get().getStatus()== EnrollmentStatus.ACCEPTED)
            {    return ResponseEntity.status(HttpStatus.CONFLICT).body("Student with ID: " + studentId
                    + " is already enrolled in course with ID: " + courseId);}
            else if (existingRequest.get().getStatus()== EnrollmentStatus.PENDING){
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("There is already an enrollment request for student ID: " + studentId
                                + " and course ID: " + courseId);
            }

        }

        // Proceed with enrolling the student if there is no existing enrollment request

        Course course = courseRepository.findByIdAndStatus(courseId, CourseStatus.APPROVED).orElse(null);

        if(course == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID: "+courseId+" doesn't exist");
        }


        if (course != null && !course.getEnrolledStudentIds().contains(studentId)) {
            if (course.getCapacity() == 0) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Course with ID: " + courseId + " is full");
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setStudentId(studentId);
            enrollmentRepository.save(enrollment);

            courseRepository.save(course);
            return ResponseEntity.status(HttpStatus.OK).body("Student with ID: " + studentId
                    + " has request to enroll in course with ID: " + courseId);

    }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling student");
    }
    public List<Course> getEnrolledCoursesForStudent(Long studentId) {
        return courseRepository.findByEnrolledStudentIdsContains(studentId);
    }



}
