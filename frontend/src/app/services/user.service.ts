import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Instructor, Student } from '../models/users.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/admin'; 

  constructor(private http: HttpClient) { }

  editStudent(student: Student): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/students`, student);
  }

  editInstructor(instructor: Instructor): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/instructors`, instructor);
  }

  // getAllUsers(): Observable<User[]> {
  //   return this.http.get<User[]>(`${this.apiUrl}/users`);
  // }

  getAllInstructors(): Observable<Instructor[]> {
    return this.http.get<Instructor[]>(`${this.apiUrl}/instructors`);
  }

  getAllStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/students`);
  }

  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/user/${userId}`);
  }

  // editUser(user: User): Observable<User> {
  //   return this.http.put<User>(`${this.apiUrl}/edituser`, user);
  // }
}
