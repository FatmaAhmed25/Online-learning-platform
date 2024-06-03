import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Course } from '../models/course.model';
import { StudentService } from '../services/student.service';
import { NotificationService } from '../services/notficationservice.service';
import { Notification } from '../models/notfication.model';
import { CourseService } from '../services/course.service';
import { Review } from '../models/review.model';
@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent {
  studentId: any | undefined ;
  courses: Course[] | undefined;
  notifications!: Notification[]; 
  selectedCourse: Course | null = null;
  reviewAndRatingMode: boolean = false;
  reviewText: string = '';
  rating: number = 0;
  showNotifications: boolean = false;




  constructor(private route: ActivatedRoute, private studentService:StudentService,private notificationService: NotificationService,private courseService:CourseService) {}

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
// Add this method in your component class
toggleNotifications(): void {
  this.showNotifications = !this.showNotifications;
}

getNotifications(): void {
  console.log('Getting notifications...');
  console.log(this.studentId)
  this.notificationService.getNotifications(this.studentId)
    .subscribe(notifications => this.notifications = notifications);
}
openReviewAndRatingBox(course: Course) {
  this.selectedCourse = course;
  this.reviewAndRatingMode = true;
}

submitReviewAndRating(course: Course) {
  // Check if courseId is defined
  if (course.id !== undefined) {
      const review: Review = {
          comment: this.reviewText,
          rating: this.rating,
          studentId: this.studentId // Assuming you have the studentId available in the course object
      };

      this.courseService.addReviewToCourse(course.id, review).subscribe(
          (response) => {
              console.log('Review and rating submitted successfully:', response);
              // Reset review and rating box
              this.reviewText = '';
              this.rating = 0;
              this.reviewAndRatingMode = false;
              this.selectedCourse = null;
          },
          (error) => {
              console.error('Error submitting review and rating:', error);
              // Handle error as needed
          }
      );
  } else {
      console.error('Course ID is undefined');
      // Handle the case where course.id is undefined, perhaps by displaying an error message to the user
  }
}


}
