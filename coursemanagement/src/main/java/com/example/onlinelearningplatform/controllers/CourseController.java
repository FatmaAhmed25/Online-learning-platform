package com.example.onlinelearningplatform.controllers;


import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;
    @GetMapping("/count")
    public long getNumberOfCourses() {
        return courseService.count();
    }

    @GetMapping("/accepted")
    public ResponseEntity<List<Course>> getAcceptedCourses() {
        List<Course> courses = courseService.getAcceptedCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping()
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @GetMapping("/search/name")
    public ResponseEntity<List<Course>> searchCoursesByName(@RequestParam String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search/category/{category}")
    public ResponseEntity<List<Course>> getCoursesByCategory(@PathVariable String category) {
        List<Course> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/search/top-rated")
    public ResponseEntity<List<Course>> getTopRatedCourses() {
        List<Course> courses = courseService.getTopRatedCourses();
        return ResponseEntity.ok(courses);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Course> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Optional<Course> updatedCourse = courseService.editCourse(id, course);

        return updatedCourse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search/top-rated/name/{name}")
    public ResponseEntity<List<Course>> searchByNameAndSort(@PathVariable String name) {
        List<Course> courses = courseService.searchByNameAndSort(name);
        return ResponseEntity.ok(courses);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/search/top-rated/category/{name}")
    public ResponseEntity<List<Course>> searchByCategoryAndSort(@PathVariable String name) {
        List<Course> courses = courseService.searchByCategoryAndSort(name);
        return ResponseEntity.ok(courses);
    }

}
