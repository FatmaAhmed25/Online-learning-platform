import { Component } from '@angular/core';
import { CourseService } from '../services/course.service';
import { Course } from '../models/course.model';
import { Review } from '../models/review.model';

@Component({
  selector: 'app-courses',
  templateUrl: './courses.component.html',
  styleUrls: ['./courses.component.css']
})
export class CoursesComponent {
  courses: Course[] = [];
  selectedCourseId: number | null = null;
  selectedCourseReviews: Review[] = [];

  constructor(private courseService: CourseService) { }

  ngOnInit(): void {
    this.getAllCourses();
  }

  getAllCourses(): void {
    this.courseService.getAllCourses().subscribe(courses => {
      this.courses = courses;
    });
  }

  editCourse(courseId: number | undefined, course: Course): void {
    if (courseId !== undefined) {
      this.courseService.updateCourse(courseId, course).subscribe(
        () => {
          console.log('Course updated successfully');
          this.getAllCourses();
        },
        error => {
          console.error('Error updating course:', error);
        }
      );
    } else {
      console.error('Invalid course ID');
    }
  }
  
  

  deleteCourse(courseId: number | undefined): void {
    if (typeof courseId === 'number') {
    
      this.courseService.deleteCourse(courseId).subscribe(
        () => {
          console.log('Course deleted successfully');
          this.getAllCourses();
        },
        error => {
          console.error('Error deleting course:', error);
        }
      );
    } else {
      console.error('Invalid course ID');
    }
  }
  showReviews(courseId: number) {
    if (this.selectedCourseId === courseId) {
      this.selectedCourseId = null;
      this.selectedCourseReviews = [];
    } else {
      this.selectedCourseId = courseId;
      this.courseService.getReviewsForCourse(courseId).subscribe(
        (reviews) => {
          this.selectedCourseReviews = reviews;
        },
        (error) => {
          console.error('Error fetching reviews:', error);
        }
      );
    }
  }
  
}