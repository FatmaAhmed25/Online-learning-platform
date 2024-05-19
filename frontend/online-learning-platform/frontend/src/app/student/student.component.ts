import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../models/course.model';
import { StudentService } from '../services/student.service';
import { NotificationService } from '../services/notficationservice.service';
import { Notification } from '../models/notfication.model';
@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent {
  studentId: any | undefined ;
  courses: Course[] | undefined;
  notifications!: Notification[]; 


  constructor(private route: ActivatedRoute, private studentService:StudentService,private notificationService: NotificationService) {}

  ngOnInit(): void {
    // Retrieve the student ID from the route parameters
  //   this.route.params.subscribe(params => {
  //     this.studentId = params['studentId'];
  //   });
  // }
  this.route.params.subscribe(params => {
    this.studentId = +params['id'];
  });
  this.fetchAvailableCourses();
  // this.getNotifications();

}
fetchAvailableCourses(): void {
  {

    this.studentService.getEnrolledCourses(this.studentId).subscribe(
      (courses: Course[]) => {
        this.courses = courses;
        console.log(this.courses)
      },
      (error) => {
        console.error('Error fetching available courses:', error);
      }
    );
  }
}

getNotifications(): void {
  console.log('Getting notifications...');
  console.log(this.studentId)
  this.notificationService.getNotifications(this.studentId)
    .subscribe(notifications => this.notifications = notifications);
}
}
