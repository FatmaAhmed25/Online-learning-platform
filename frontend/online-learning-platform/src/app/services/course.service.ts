// course.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private baseUrl = 'http://localhost:8081'; 

  constructor(private http: HttpClient) {}

  createCourse(courseData: any, instructorId: string | null): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/instructor/createcourse?instructorId=${instructorId}`, courseData);
  }
  getAllCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses`);
  }

  searchCoursesByName(name: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/name?name=${name}`);
  }

  getCoursesByCategory(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/category/${category}`);
  }

  getTopRatedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/top-rated`);
  }

  searchByNameAndSort(name: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/top-rated/name/${name}`);
  }

  searchByCategoryAndSort(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/top-rated/category/${category}`);
  }
}
