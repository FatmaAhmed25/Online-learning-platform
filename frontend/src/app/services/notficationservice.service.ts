import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Notification } from '../models/notfication.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = 'http://localhost:8081'; 

  constructor(private http: HttpClient) { }

  getNotifications(studentId: number): Observable<Notification[]> {
    const url = `${this.baseUrl}/notifications/${studentId}`;
    return this.http.get<Notification[]>(url);
  }
}
