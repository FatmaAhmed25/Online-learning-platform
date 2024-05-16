package com.example.onlinelearningplatform.controllers;



import com.example.onlinelearningplatform.models.Course;
import com.example.onlinelearningplatform.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @PostMapping("/createcourse")
    public ResponseEntity<Object> createCourse(@RequestBody Course course, @RequestParam Long instructorId) {
        return  instructorService.createCourse(course,instructorId);
    }




}
