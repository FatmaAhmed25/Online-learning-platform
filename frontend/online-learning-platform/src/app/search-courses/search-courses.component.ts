import { Component, OnInit } from '@angular/core';
import { CourseService } from '../services/course.service';
import { Course } from '../models/course.model';

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

  constructor(private courseService: CourseService) {}

  ngOnInit() {
    this.fetchAllCourses();
  }

  fetchAllCourses() {
    this.courseService.getAllCourses().subscribe(
      (courses) => {
        this.courses = courses;
        //this.filteredCourses = courses;
      },
      (error) => {
        console.error('Error fetching courses:', error);
      }
    );
  }

  searchCourses() {
    console.log("Search Query:", this.searchQuery);
    console.log("Search Category:", this.searchCategory);
    console.log("Sort Option:", this.sortOption);
    console.log("here")
    if (this.sortOption === 'rating') {
      console.log(this.searchQuery)
      if (this.searchQuery) {
        console.log("type")
        this.courseService.searchByNameAndSort(this.searchQuery).subscribe(
          (courses) => {
            this.courses = courses; 
           // this.filteredCourses = courses;
          },
          (error) => {
            console.error('Error searching courses by name and sorting by rating:', error);
          }
          
        );
      } else if (this.searchCategory) {
        this.courseService.searchByCategoryAndSort(this.searchCategory).subscribe(
          (courses) => {
            this.courses = courses; 
           // this.filteredCourses = courses;
          },
          (error) => {
            console.error('Error searching courses by category and sorting by rating:', error);
          }
        );
      } else {
        this.courseService.getTopRatedCourses().subscribe(
          (courses) => {
            this.courses = courses; 
           // this.filteredCourses = courses;
          },
          (error) => {
            console.error('Error fetching top rated courses:', error);
          }
        );
      }
    } else {
      if (this.searchQuery) {
        console.log("yes")
        this.courseService.searchCoursesByName(this.searchQuery).subscribe(
          (courses) => {
            this.courses = courses; 
           // this.filteredCourses = courses;
          },
          (error) => {
            console.error('Error searching courses by name:', error);
          }
        );
      } else if (this.searchCategory) {
        this.courseService.getCoursesByCategory(this.searchCategory).subscribe(
          (courses) => {
            this.courses = courses; 
           // this.filteredCourses = courses;
          },
          (error) => {
            console.error('Error searching courses by category:', error);
          }
        );
      } else {
        this.courses = this.courses; 
        //this.filteredCourses = this.courses;
      }
    }
  }
}
