package com.example.onlinelearningplatform.services;


import com.example.onlinelearningplatform.models.*;
import com.example.onlinelearningplatform.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAcceptedCourses() {
        return courseRepository.findByStatus(CourseStatus.APPROVED);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }



    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findByIdAndStatus(id, CourseStatus.APPROVED);
    }


    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCaseAndStatus(name, CourseStatus.APPROVED);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategoryContainingIgnoreCaseAndStatus(category, CourseStatus.APPROVED);
    }

    public List<Course> getTopRatedCourses() {
        return courseRepository.findByStatusOrderByRatingDesc(CourseStatus.APPROVED);
    }


    public long count() {
        return courseRepository.countByStatus(CourseStatus.APPROVED);
    }
    public List<Course> getAvailableCoursesForStudent(Long studentId) {
        return courseRepository.findAvailableCoursesForStudent(studentId);
    }
    public Optional<Course> editCourse(Long courseId, Course request) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();

            if (request.getInstructorId() != null) {
                course.setInstructorId(request.getInstructorId());
            }
            if (request.getName() != null) {
                course.setName(request.getName());
            }
            if (request.getCategory() != null) {
                course.setCategory(request.getCategory());
            }
            if (request.getDuration() != null && request.getDuration() > 0) {
                course.setDuration(request.getDuration());
            }
            if (request.getRating() != null && request.getRating() >= 0) {
                course.setRating(request.getRating());
            }
            if (request.getCapacity() != null && request.getCapacity() > 0) {
                course.setCapacity(request.getCapacity());
            }
            if (request.getAdminId() != null) {
                course.setAdminId(request.getAdminId());
            }
            if (request.getStatus() != null) {
                course.setStatus(request.getStatus());
            }

            courseRepository.save(course);
            return Optional.of(course);
        } else {
            return Optional.empty();
        }
    }

    public List<Course> searchByNameAndSort(String name) {
        return courseRepository.findByNameContainingIgnoreCaseAndStatusOrderByRatingDesc(name,CourseStatus.APPROVED);
    }

    public void deleteCourse(Long courseId) {
        if (courseRepository.existsById(courseId)) {
            courseRepository.deleteById(courseId);
        } else {
            throw new NotFoundException("Course not found");
        }
    }
    public List<Course> searchByCategoryAndSort(String name) {
        return courseRepository.findByCategoryContainingIgnoreCaseAndStatusOrderByRatingDesc(name,CourseStatus.APPROVED);
    }

}
