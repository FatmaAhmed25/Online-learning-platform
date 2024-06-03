import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  // Method to register a student
  registerStudent(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/register/student', user);
  }

  // Method to register an instructor
  registerInstructor(user: User): Observable<any> {
    return this.http.post<any>('http://localhost:8080/Online-Learning-Platform-3.0-SNAPSHOT/api/register/instructor', user);
  }
}
