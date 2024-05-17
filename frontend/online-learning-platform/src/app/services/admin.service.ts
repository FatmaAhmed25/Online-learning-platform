import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private userManagementURL = 'http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api'; 
  private courseManagementURL = 'http://localhost:8081'; 


  constructor(private http: HttpClient) { }

  approveCourse(courseId: number, adminId: number): Observable<any> {
    return this.http.post<any>(`${this.userManagementURL}/admin/approve/${courseId}`, { adminId });
  }

  rejectCourse(courseId: number, adminId: number): Observable<any> {
    return this.http.post<any>(`${this.userManagementURL}/admin/reject/${courseId}`, { adminId });
  }

  getCoursesPending(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.courseManagementURL}/admin/pending`);
  }

  getAcceptedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.courseManagementURL}/admin/accepted`);
  }
  getRejectedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.courseManagementURL}/admin/rejected`);
  }

  getNumberOfCourses(): Observable<number> {
    return this.http.get<number>(`${this.courseManagementURL}/courses/count`);
  }
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.courseManagementURL}/courses`);
  }
  getNumberOfUsers(): Observable<number> {
    return this.http.get<number>(`${this.userManagementURL}/users/count`);
  }
  getNumberOfStudents(): Observable<number> {
    return this.http.get<number>(`${this.userManagementURL}/student/count`);
  }
  getNumberOfInstructors(): Observable<number> {
    return this.http.get<number>(`${this.userManagementURL}/instructor/count`);
  }
}
