// create-course.component.ts

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CourseService } from '../services/course.service'; // Assuming you have a service for course-related operations

@Component({
  selector: 'app-create-course',
  templateUrl: './create-course.component.html',
  styleUrls: ['./create-course.component.css']
})
export class CreateCourseComponent implements OnInit {
  instructorId: string | null=null;
  showApprovalMessage: boolean = false;

  constructor(private route: ActivatedRoute, private courseService: CourseService) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.instructorId = params['instructorId'];
    });
  }

  createCourse(courseData: any): void {
    this.courseService.createCourse(courseData, this.instructorId).subscribe(response => {
      console.log('Course created successfully:', response);
      // Show approval message after course creation
      this.showApprovalMessage = true;
    }, error => {
      console.error('Error creating course:', error);
    });
  }
}
