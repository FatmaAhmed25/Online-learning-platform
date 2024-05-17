import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Enrollment } from '../models/Enrollment.model';



@Injectable({
  providedIn: 'root'
})
export class InstructorService {
  private baseUrl = 'http://localhost:8081'; // Base URL

  constructor(private http: HttpClient) { }

  // Function to get count of approved courses
  getCountOfApprovedCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/approved-count`);
  }

  // Function to get count of pending courses
  getCountOfPendingCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/pending-count`);
  }

  // Function to get pending enrollments for instructor
  getPendingEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/enrollment-requests/${instructorId}`);
  }

  // Function to get approved enrollments for instructor
  getApprovedEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/approved-enrollment-requests/${instructorId}`);
  }

  // Function to get rejected enrollments for instructor
  getRejectedEnrollments(instructorId: number): Observable<Enrollment[]> {
    return this.http.get<Enrollment[]>(`${this.baseUrl}/enrollments/rejected-enrollment-requests/${instructorId}`);
  }

  // Function to approve an enrollment
  approveEnrollment(enrollmentId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/enrollments/approve/${enrollmentId}`, {});
  }

  // Function to reject an enrollment
  rejectEnrollment(enrollmentId: number): Observable<any> {
    return this.http.put<any>(`${this.baseUrl}/enrollments/reject/${enrollmentId}`, {});
  }
}
