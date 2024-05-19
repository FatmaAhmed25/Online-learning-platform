// search-courses.component.ts
import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course.service';
import { Course} from '../models/course.model';
import {  Review } from '../models/review.model';
@Component({
  selector: 'app-search-courses',
  templateUrl: './search-courses.component.html',
  styleUrls: ['./search-courses.component.css']
})
export class SearchCoursesComponent implements OnInit {
  searchQuery: string = '';
  searchCategory: string = '';
  sortOption: string = '';
  courses: Course[] = [];
  filteredCourses: Course[] = [];
  selectedCourseId: number | null = null;
  selectedCourseReviews: Review[] = [];

  constructor(private courseService: CourseService) {}

  ngOnInit() {
    this.fetchAllCourses();
  }

  fetchAllCourses() {
    this.courseService.getAllCourses().subscribe(
      (courses) => {
        this.courses = courses;
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  searchCourses() {
    if (this.sortOption === 'rating') {
      if (this.searchQuery) {
        this.courseService.searchByNameAndSort(this.searchQuery).subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error searching courses by name and sorting by rating:', error);
          }
        );
      } else if (this.searchCategory) {
        this.courseService.searchByCategoryAndSort(this.searchCategory).subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error searching courses by category and sorting by rating:', error);
          }
        );
      } else {
        this.courseService.getTopRatedCourses().subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error fetching top rated courses:', error);
          }
        );
      }
    } else {
      if (this.searchQuery) {
        this.courseService.searchCoursesByName(this.searchQuery).subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error searching courses by name:', error);
          }
        );
      } else if (this.searchCategory) {
        this.courseService.getCoursesByCategory(this.searchCategory).subscribe(
          (courses) => {
            this.courses = courses;
          },
          (error) => {
            console.error('Error searching courses by category:', error);
          }
        );
      }
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
