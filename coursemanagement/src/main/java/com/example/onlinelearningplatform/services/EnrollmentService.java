package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.models.Enrollment;
import com.example.onlinelearningplatform.models.EnrollmentStatus;
import com.example.onlinelearningplatform.models.Notification;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import com.example.onlinelearningplatform.repositories.EnrollmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private NotificationService notificationService;
    public ResponseEntity<String> approveEnrollment(Long enrollmentId) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElse(null);

        //enrollment exists and is not already approved
        if (enrollment != null && !enrollment.getStatus().equals(EnrollmentStatus.ACCEPTED)) {
            // Mark the enrollment request as approved
            enrollment.setStatus(EnrollmentStatus.ACCEPTED);
            enrollmentRepository.save(enrollment);

            // Retrieve course and student IDs
            Long courseId = enrollment.getCourseId();
            Long studentId = enrollment.getStudentId();

            // Retrieve the course
            Course course = courseRepository.findById(courseId).orElse(null);
            if (course != null) {
                // Update the enrolled student IDs
                Set<Long> enrolledStudentIds = course.getEnrolledStudentIds();
                enrolledStudentIds.add(studentId);
                course.setNumberOfEnrolledStudents(course.getNumberOfEnrolledStudents()+1);
                course.setEnrolledStudentIds(enrolledStudentIds);
                course.setCapacity(course.getCapacity() - 1);
                courseRepository.save(course);
            }

            Notification notification = new Notification();
            notification.setMessage("Your enrollment for course of id: " + enrollment.getCourseId()+" has been approved");
            notification.setTimestamp(LocalDateTime.now());
            notification.setStudentId(enrollment.getStudentId());

            // Send notification
            notificationService.sendNotification(notification);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Enrollment request with ID " + enrollmentId + " has been approved.");
        } else if (enrollment != null && enrollment.getStatus().equals(EnrollmentStatus.ACCEPTED)) {
            // Enrollment request is already approved
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Enrollment request with ID " + enrollmentId + " is already approved.");
        } else {
            //  when enrollment is  not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment request with ID " + enrollmentId + " not found.");
        }
    }


    public ResponseEntity<String> rejectEnrollment(Long enrollmentId) {
        Optional<Enrollment> optionalEnrollment = enrollmentRepository.findById(enrollmentId);
        if (optionalEnrollment.isPresent()) {
            Enrollment enrollment = optionalEnrollment.get();

            // if the enrollment is not already approved

            if (enrollment.getStatus().equals(EnrollmentStatus.ACCEPTED)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Enrollment request with ID " + enrollmentId + " is already approved. It cannot be rejected.");
            }
            else if (enrollment.getStatus().equals(EnrollmentStatus.REJECTED))
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Enrollment request with ID " + enrollmentId + " is already REJECTED.");
            }
            else
            {
            // Set the enrollment as rejected
            enrollment.setStatus(EnrollmentStatus.REJECTED);
            enrollmentRepository.save(enrollment);

            Notification notification = new Notification();
            notification.setMessage("Your enrollment for course of id: " + enrollment.getCourseId()+" has been rejected");
            notification.setTimestamp(LocalDateTime.now());
            notification.setStudentId(enrollment.getStudentId());

                // Send notification
                notificationService.sendNotification(notification);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Enrollment request with ID " + enrollmentId + " has been rejected.");
        }}

        else {
            // when enrollment is null or not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Enrollment request with ID " + enrollmentId + " not found.");
        }
    }

    public List<Enrollment> getPendingRequestsForInstructor(Long instructorId) {
        // Retrieve all course IDs owned by the instructor
        List<Course> instructorCourses = courseRepository.findByInstructorId(instructorId);
        Set<Long> courseIds = instructorCourses.stream().map(Course::getId).collect(Collectors.toSet());

        //enrollment requests for courses owned by the instructor
        return enrollmentRepository.findByCourseIdInAndStatus(courseIds,EnrollmentStatus.PENDING);
    }

    public List<Enrollment> getApprovedRequestsForInstructor(Long instructorId) {
        // Retrieve all course IDs owned by the instructor
        List<Course> instructorCourses = courseRepository.findByInstructorId(instructorId);
        Set<Long> courseIds = instructorCourses.stream().map(Course::getId).collect(Collectors.toSet());

        //enrollment requests for courses owned by the instructor
        return enrollmentRepository.findByCourseIdInAndStatus(courseIds,EnrollmentStatus.ACCEPTED);
    }
    public List<Enrollment> getRejectedEnrollmentRequestsForInstructor(Long instructorId) {
        // Retrieve all course IDs owned by the instructor
        List<Course> instructorCourses = courseRepository.findByInstructorId(instructorId);
        Set<Long> courseIds = instructorCourses.stream().map(Course::getId).collect(Collectors.toSet());

        //enrollment requests for courses owned by the instructor
        return enrollmentRepository.findByCourseIdInAndStatus(courseIds,EnrollmentStatus.REJECTED);
    }
    public List<Enrollment> getEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getAcceptedEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findByStudentIdAndStatus(studentId, EnrollmentStatus.ACCEPTED);
    }

    public List<Enrollment> getRejectedEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findByStudentIdAndStatus(studentId, EnrollmentStatus.REJECTED);
    }

    public List<Enrollment> getPendingEnrollmentsForStudent(Long studentId) {
        return enrollmentRepository.findByStudentIdAndStatus(studentId, EnrollmentStatus.PENDING);
    }

    //da law hcancel ay enrollment(accepted,rejected,pending)
    @Transactional
    public void cancelEnrollment(Long studentId, Long courseId) {
        enrollmentRepository.deleteByStudentIdAndCourseIdAndStatus(studentId, courseId, EnrollmentStatus.PENDING);
    }

    @Transactional
    public void cancelPendingEnrollment(Long studentId, Long courseId)
    {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (enrollmentOptional.isPresent()) {
            Enrollment enrollment = enrollmentOptional.get();
            if (enrollment.getStatus() == EnrollmentStatus.PENDING) {
                enrollmentRepository.delete(enrollment);
            } else {
                throw new IllegalStateException("Enrollment is not pending and cannot be canceled.");
            }
        } else {
            throw new IllegalArgumentException("Enrollment not found for the given student and course.");
        }
    }








//    public Enrollment approveEnrollment(Long instructorId, Long enrollmentId) {
//        Enrollment enrollment = enrollmentRepository.findById(enrollmentId).orElse(null);
//        if (enrollment != null && !enrollment.isApproved()) {
//            Course course = courseRepository.findById(enrollment.getCourseId()).orElse(null);
//            if (course != null && course.getInstructorId().equals(instructorId)) {
//                enrollment.setApproved(true);
//                enrollmentRepository.save(enrollment);
//                return enrollment;
//            } else {
//                // Instructor does not own the course, return error
//                throw new IllegalStateException("Instructor does not have permission to approve enrollment.");
//            }
//        } else {
//            // Enrollment not found or already approved, return error or handle accordingly
//            throw new IllegalArgumentException("Invalid enrollment ID or enrollment already approved.");
//        }
//    }




}
