// course.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';
import { Review } from '../models/review.model';
@Injectable({
  providedIn: 'root'
})
export class CourseService {
  private baseUrl = 'http://localhost:8081'; 

  constructor(private http: HttpClient) {}

  createCourse(courseData: any, instructorId: string | null): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/instructor/createcourse?instructorId=${instructorId}`, courseData);
  }
  getAcceptedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/accepted`);
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
  updateCourse(courseId: number, courseData: any): Observable<Course> {
    return this.http.put<Course>(`${this.baseUrl}/courses/edit/${courseId}`, courseData);
  }
  addReviewToCourse(courseId: number, review: Review): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/reviews/courses/${courseId}/students/${review.studentId}`, review);
  }

  deleteCourse(courseId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/courses/delete/${courseId}`);
  }

  searchByCategoryAndSort(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/courses/search/top-rated/category/${category}`);
  }
  getReviewsForCourse(courseId: number): Observable<Review[]> {
    return this.http.get<Review[]>(`${this.baseUrl}/reviews/courses/${courseId}`);
  }
}
