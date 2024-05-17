import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  private baseUrl = 'http://localhost:8081'; // Base URL for your API

  constructor(private http: HttpClient) { }

  // Function to get count of approved courses
  getCountOfApprovedCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/approved-count`);
  }

  // Function to get count of pending courses
  getCountOfPendingCourses(instructorId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/instructor/${instructorId}/pending-count`);
  }
}
