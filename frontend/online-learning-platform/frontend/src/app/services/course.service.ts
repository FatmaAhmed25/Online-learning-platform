// course.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private baseUrl = 'http://localhost:8081'; 

  constructor(private http: HttpClient) {}

  createCourse(courseData: any, instructorId: string | null): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/instructor/createcourse?instructorId=${instructorId}`, courseData);
  }
}
