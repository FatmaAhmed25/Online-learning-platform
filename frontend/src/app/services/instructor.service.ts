import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Enrollment } from '../models/enrollment.model';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  private baseUrl = 'http://localhost:8081'; // Base URL for API

  constructor(private http: HttpClient) { }

  
  getCountOfApprovedCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/approved-count`);
  }


  getCountOfPendingCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/pending-count`);
  }

  getPendingEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/enrollment-requests/${instructorId}`);
  }

  getApprovedEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/approved-enrollment-requests/${instructorId}`);
  }

 
  getRejectedEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/rejected-enrollment-requests/${instructorId}`);
  }


  approveEnrollment(enrollmentId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/enrollments/approve/${enrollmentId}`, {});
  }

  rejectEnrollment(enrollmentId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/enrollments/reject/${enrollmentId}`, {});
  }
}
