import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../models/course.model';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent {

  studentId: string | undefined ='9';

  constructor(private route: ActivatedRoute,private studentService: StudentService) {}

  ngOnInit(): void {
    // Retrieve the student ID from the route parameters
    // this.route.params.subscribe(params => {
    //   this.studentId = params['studentId'];
    //   console.log(this.studentId);
    // });
    this.fetchAvailableCourses();
  }
  courses: Course[] = [];

  fetchAvailableCourses(): void {
      {
  
        this.studentService.getAllCourses().subscribe(
          (courses: Course[]) => {
            this.courses = courses;
            console.log(this.courses)
          },
          (error) => {
            console.error('Error fetching available courses:', error);
          }
        );
      }
    }
    enroll(courseId: any) {
      this.studentService.enrollStudent(this.studentId, courseId).subscribe(
          (response) => {
              // Handle successful enrollment
              console.log("Enrollment successful!");
          },
          (error) => {
              // Handle enrollment error
              console.error("Enrollment failed:", error);
          }
      );
  }
  
}
