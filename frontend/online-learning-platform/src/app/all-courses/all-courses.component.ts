import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../models/course.model';
import { StudentService } from '../services/student.service';
import { SearchService } from '../services/search.service';

@Component({
  selector: 'app-all-courses',
  templateUrl: './all-courses.component.html',
  styleUrls: ['./all-courses.component.css']
})
export class AllCoursesComponent {


  studentId: string | undefined ='9';
  selectedFilter: string = 'name';

  constructor(private route: ActivatedRoute,private studentService: StudentService, private searchService:SearchService) {}

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
  onFilterChange() {
    // Clear search input when filter changes
    const searchInput = document.getElementById('searchInput') as HTMLInputElement;
    searchInput.value = '';
    this.search();
  }
  search() {
    const searchInput = document.getElementById('searchInput') as HTMLInputElement;
    const searchTerm = searchInput.value.trim(); // Trim whitespace from input value

    if (searchTerm !== '') {
      if (this.selectedFilter === 'name') {
        this.searchService.searchCoursesByName(searchTerm).subscribe(
          (data) => {
            this.courses = data;
          },
          (error) => {
            console.error('Error fetching courses by name:', error);
          }
        );
      } else if (this.selectedFilter === 'category') {
        this.searchService.getCoursesByCategory(searchTerm).subscribe(
          (data) => {
            this.courses = data;
          },
          (error) => {
            console.error('Error fetching courses by category:', error);
          }
        );
      }
    }
  }

  searchandSort() {

    const searchInput = document.getElementById('searchInput') as HTMLInputElement;
    const searchTerm = searchInput.value.trim(); // Trim whitespace from input value
    if( searchTerm === '')
      {
        this.searchService.getTopRatedCourses().subscribe(
          (data) => {
            this.courses = data;
          },
          (error) => {
            console.error('Error fetching courses by name:', error);
          }
        );
      }else {
      if (this.selectedFilter === 'name') {
        this.searchService.searchByNameAndSort(searchTerm).subscribe(
          (data) => {
            console.log('here');
            this.courses = data;
          },
          (error) => {
            console.error('Error fetching courses by name:', error);
          }
        );
      } else if (this.selectedFilter === 'category') {
        this.searchService.searchByCategoryAndSort(searchTerm).subscribe(
          (data) => {
            console.log(data);
            this.courses = data;
          },
          (error) => {
            console.error('Error fetching courses by category:', error);
          }
        );
      }
    }
    
    }
  
}
