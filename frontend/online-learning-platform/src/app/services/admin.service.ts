import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};
@Injectable({
  providedIn: 'root'

})

export class AdminService {

  private userManagementURL = 'http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api'; 
  private courseManagementURL = 'http://localhost:8081'; 

  constructor(private http: HttpClient) { }

  approveCourse(courseId: number, adminId: number): Observable<any> {
    // Construct URL with courseId as path parameter
    const url = `${this.courseManagementURL}/admin/approve/${courseId}`;

    // Create HttpParams object for request parameters
    const params = new HttpParams().set('adminId', adminId.toString());

    // Make the HTTP POST request with the specified URL and request parameters
    return this.http.post<any>(url, null, { params: params });
  }

  rejectCourse(courseId: number, adminId: any): Observable<any> {
    // Construct URL with courseId as path parameter
    const url = `${this.courseManagementURL}/admin/reject/${courseId}`;

    // Create HttpParams object for request parameters
    const params = new HttpParams().set('adminId', adminId.toString());

    // Make the HTTP POST request with the specified URL and request parameters
    return this.http.post<any>(url, null, { params: params });
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
