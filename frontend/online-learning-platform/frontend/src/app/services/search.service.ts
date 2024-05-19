import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../models/course.model';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  baseUrl = 'http://localhost:8081/courses';

  constructor(private http: HttpClient) { }

  searchCoursesByName(name: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/search/name?name=${name}`);
  }

  getCoursesByCategory(category: string): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/search/category/${category}`);
  }

  getTopRatedCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/search/top-rated`);
  }
  searchByNameAndSort(name :any): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/search/top-rated/name/${name}`);
  }
  searchByCategoryAndSort(name :any): Observable<Course[]> {
    return this.http.get<Course[]>(`${this.baseUrl}/search/top-rated/category/${name}`);
  }
}
