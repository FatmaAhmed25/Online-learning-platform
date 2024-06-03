import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../services/student.service';
import { Enrollment } from '../models/enrollment.model';

@Component({
  selector: 'app-student-enrollments',
  templateUrl: './student-enrollments.component.html',
  styleUrls: ['./student-enrollments.component.css','../instructor/instructor.component.css']
})
export class StudentEnrollmentsComponent implements OnInit {
  
  studentId: any | undefined;
  acceptedEnrollments: Enrollment[] =[];
  rejectedEnrollments: Enrollment[] =[];
  pendingEnrollments: Enrollment[] =[];
  selectedTab: string = 'pending';

  constructor(private route: ActivatedRoute, private studentService: StudentService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.studentId = +params['id'];
      this.loadEnrollments();
    });
  }

  loadEnrollments() {
    this.studentService.getAcceptedEnrollmentsForStudent(this.studentId).subscribe(
      (data) => {
        this.acceptedEnrollments = data;
      },
      (error) => {
        console.error('Error fetching accepted enrollments:', error);
      }
    );

    this.studentService.getRejectedEnrollmentsForStudent(this.studentId).subscribe(
      (data) => {
        this.rejectedEnrollments = data;
      },
      (error) => {
        console.error('Error fetching rejected enrollments:', error);
      }
    );

    this.studentService.getPendingEnrollmentsForStudent(this.studentId).subscribe(
      (data) => {
        this.pendingEnrollments = data;
      },
      (error) => {
        console.error('Error fetching pending enrollments:', error);
      }
    );
  }

  cancelEnrollment(courseId: number) {
    this.studentService.cancelPendingEnrollment(this.studentId, courseId).subscribe(
      (response) => {
        console.log(response);
        // Reload enrollments after cancellation
        this.loadEnrollments();
      },
      (error) => {
        console.error('Error canceling enrollment:', error);
      }
    );
  }
  selectTab(tab: string): void {
    this.selectedTab = tab;
  }
  getFilteredEnrollments(): Enrollment[] {
    switch (this.selectedTab) {
      case 'accepted':
        return this.acceptedEnrollments;
      case 'rejected':
        return this.rejectedEnrollments;
      case 'pending':
      default:
        return this.pendingEnrollments;
    }
  }


  // getFilteredEnrollments(): Enrollment[] {
  //   switch (this.selectedTab) {
  //     case 'accepted':
  //       return this.acceptedEnrollments;
  //     case 'rejected':
  //       return this.rejectedEnrollments;
  //     case 'pending':
  //     default:
  //       return this.pendingEnrollments;
  //   }
  // }
}
