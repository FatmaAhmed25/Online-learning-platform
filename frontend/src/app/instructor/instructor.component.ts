import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InstructorService } from '../services/instructor.service';
import { Enrollment } from '../models/enrollment.model';


@Component({
  selector: 'app-instructor',
  templateUrl: './instructor.component.html',
  styleUrls: ['./instructor.component.css']
})
export class InstructorComponent implements OnInit {
  instructorId: number | null = null;
  approvedCoursesCount: number = 0;
  pendingCoursesCount: number = 0;
  pendingEnrollments: Enrollment[] = [];
  acceptedEnrollments: Enrollment[] = [];
  rejectedEnrollments: Enrollment[] = [];
  selectedTab: string = 'pending';

  constructor(private route: ActivatedRoute, private instructorService: InstructorService) {}

  ngOnInit(): void {
    this.fetchEnrollments();
    this.route.params.subscribe(params => {
      this.instructorId = +params['id'];
      this.fetchCounts();
      this.fetchEnrollments();
 
    });
  }

  fetchCounts(): void {
    if (this.instructorId !== null) {
      this.instructorService.getCountOfApprovedCourses(this.instructorId).subscribe(count => {
        this.approvedCoursesCount = count;
      });

      this.instructorService.getCountOfPendingCourses(this.instructorId).subscribe(count => {
        this.pendingCoursesCount = count;
      });
    }
  }

  fetchEnrollments(): void {
    if (this.instructorId !== null) {
      this.instructorService.getPendingEnrollments(this.instructorId).subscribe(enrollments => {
        this.pendingEnrollments = enrollments;
      
      });

      this.instructorService.getApprovedEnrollments(this.instructorId).subscribe(enrollments => {
        this.acceptedEnrollments = enrollments;
      });

      this.instructorService.getRejectedEnrollments(this.instructorId).subscribe(enrollments => {
        this.rejectedEnrollments = enrollments;
      });
    }
  }

  approveEnrollment(enrollmentId: number): void {
    this.instructorService.approveEnrollment(enrollmentId).subscribe(() => {
      //this.moveEnrollment(enrollmentId, this.pendingEnrollments, this.acceptedEnrollments, 'ACCEPTED');
      this.fetchEnrollments();
      this.getFilteredEnrollments();
    });
    this.fetchEnrollments();
    this.getFilteredEnrollments()
  }

  rejectEnrollment(enrollmentId: number): void {
    this.instructorService.rejectEnrollment(enrollmentId).subscribe(() => {
      this.moveEnrollment(enrollmentId, this.pendingEnrollments, this.rejectedEnrollments, 'REJECTED');
    });
    this.fetchEnrollments();
    this.getFilteredEnrollments();
  }

  moveEnrollment(enrollmentId: number, fromList: Enrollment[], toList: Enrollment[], newStatus: string): void {
    const enrollmentIndex = fromList.findIndex(e => e.id === enrollmentId);
    if (enrollmentIndex > -1) {
      const [enrollment] = fromList.splice(enrollmentIndex, 1);
      enrollment.status = newStatus;
      toList.push(enrollment);
    }
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
}
