import { Component } from '@angular/core';
import { AdminService } from '../services/admin.service';
import { Course } from '../models/course.model';
import { CourseStatus } from '../models/courseStatus';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent {
  numberOfCourses: number | undefined;
  numberOfUsers: number | undefined;
  numberOfStudents: number | undefined;
  numberOfInstructors: number | undefined;
  pendingCourses: Course[] | undefined;
  acceptedCourses: Course[] | undefined;
  courses: Course[] | undefined;
  allCourses: Course[] | undefined;
  rejectedCourses: Course[] | undefined;
  CourseStatus = CourseStatus;

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.fetchData();
  }

  fetchData(): void {
    this.fetchNumberOfCourses();
    this.fetchNumberOfUsers();
    this.fetchNumberOfStudents();
    this.fetchNumberOfInstructors();
    this.fetchPendingCourses();
  }


  fetchNumberOfCourses(): void {
    this.adminService.getNumberOfCourses().subscribe(
      (numberOfCourses: number) => {
        this.numberOfCourses = numberOfCourses;
      },
      (error) => {
        console.error('Error fetching number of courses:', error);
      }
    );
  }
  fetchNumberOfUsers(): void {
    this.adminService.getNumberOfUsers().subscribe(
      (numberOfUsers: number) => {
      
        this.numberOfUsers = numberOfUsers;
        console.log('Number of users:', numberOfUsers);
      },
      (error) => {
        console.error('Error fetching number of users:', error);
      }
    );
  }

  fetchNumberOfStudents(): void {
    this.adminService.getNumberOfStudents().subscribe(
      (numberOfStudents: number) => {
        this.numberOfStudents = numberOfStudents;
      },
      (error) => {
        console.error('Error fetching number of students:', error);
      }
    );
  }

  fetchNumberOfInstructors(): void {
    this.adminService.getNumberOfInstructors().subscribe(
      (numberOfInstructors: number) => {
        this.numberOfInstructors = numberOfInstructors;
      },
      (error) => {
        console.error('Error fetching number of instructors:', error);
      }
    );
  }
  fetchPendingCourses(): void {
    this.adminService.getCoursesPending().subscribe(
      (courses: Course[]) => {
        this.pendingCourses = courses;
        this.courses=courses;
      },
      (error) => {
        console.error('Error fetching pending courses:', error);
      }
    );
  }

  fetchAcceptedCourses(): void {
    this.adminService.getAcceptedCourses().subscribe(
      (courses: Course[]) => {
        this.courses = courses;
        this.acceptedCourses = courses;
      },
      (error) => {
        console.error('Error fetching accepted courses:', error);
      }
    );
  }

  fetchRejectedCourses(): void {
    this.adminService.getRejectedCourses().subscribe(
      (courses: Course[]) => {
        this.acceptedCourses = courses;
        this.courses =courses;
      },
      (error) => {
        console.error('Error fetching accepted courses:', error);
      }
    );
  }

  fetchAllCourses(): void {
    this.adminService.getAllCourses().subscribe(
      (courses: Course[]) => {
        this.allCourses = courses;
        this.courses = courses;
      },
      (error) => {
        console.error('Error fetching all courses:', error);
      }
    );
  }

}
