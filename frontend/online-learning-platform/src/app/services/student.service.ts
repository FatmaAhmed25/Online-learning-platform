import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  private baseUrl = 'http://localhost:8081'; 

  constructor(private http: HttpClient) { }

  enrollStudent(studentId: any, courseId: any): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/student/enroll/${studentId}/${courseId}`, null);
  }

  getEnrolledCourses(studentId: any): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/student/enrolledCourses/${studentId}`);
  }

  getAvailableCourses(studentId: any): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/student/getAvailableCourses/${studentId}`);
  }
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses`);
  }
}
