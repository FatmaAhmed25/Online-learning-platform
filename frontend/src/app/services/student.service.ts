import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { Enrollment } from '../models/enrollment.model';

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
  getAcceptedEnrollmentsForStudent(studentId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/student/${studentId}/accepted`);
  }

  getRejectedEnrollmentsForStudent(studentId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/student/${studentId}/rejected`);
  }

  getPendingEnrollmentsForStudent(studentId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/student/${studentId}/pending`);
  }

  cancelPendingEnrollment(studentId: number, courseId: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/enrollments/${studentId}/${courseId}`);
  }
}
