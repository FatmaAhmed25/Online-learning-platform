package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.models.EnrollmentStatus;
import com.example.onlinelearningplatform.models.Notification;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private ValidationService validationService;



    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Optional<Course> getCourseById(Long id)
    {
        return courseRepository.findById(id);
    }

    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategoryContainingIgnoreCase(category);
    }

    public List<Course> getTopRatedCourses() {
        return courseRepository.findByOrderByRatingDesc();
    }

    public ResponseEntity<Object> createCourse(Course course, Long instructorId) {
        if(!validationService.validateInstructor(instructorId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instructor with ID: "+instructorId+" doesn't exist");
        }
        course.setInstructorId(instructorId); // Set the instructor ID
        Course savedCourse = courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.OK).body(savedCourse);
    }

    public ResponseEntity<String> enrollStudent(Long studentId, Long courseId) {
        if(!validationService.validateStudent(studentId))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID: "+studentId+" doesn't exist");
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
        Course course = courseRepository.findById(courseId).orElse(null);
        if(course == null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course with ID: "+courseId+" doesn't exist");
        }
        if (course != null && !course.getEnrolledStudentIds().contains(studentId)) {
            Enrollment enrollment = new Enrollment();
            enrollment.setCourseId(courseId);
            enrollment.setStudentId(studentId);
            enrollmentRepository.save(enrollment);

            courseRepository.save(course);
            // Return a success response
            return ResponseEntity.status(HttpStatus.OK).body("Student with ID: " + studentId
                    + " has request to enroll in course with ID: " + courseId);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enrolling student");
    }

    public List<Course> getEnrolledCoursesForStudent(Long studentId) {
        // Retrieve enrolled courses for the student
        return courseRepository.findByEnrolledStudentIdsContains(studentId);
    }




}
