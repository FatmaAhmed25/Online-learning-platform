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
  approvedCoursesCount: number = 0; // Initialize with default value
  pendingCoursesCount: number = 0; // Initialize with default value
  enrollments: Enrollment[] = []; // Initialize an array to store enrollments
  selectedTab: string = 'pending'; // Default tab

  constructor(private route: ActivatedRoute, private instructorService: InstructorService) {}

  ngOnInit(): void {
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
      this.instructorService.getEnrollmentRequestsForInstructor(this.instructorId).subscribe(enrollments => {
        this.enrollments = enrollments;
      });
    }
  }

  approveEnrollment(enrollmentId: number): void {
    this.instructorService.approveEnrollment(enrollmentId).subscribe(() => {
      this.fetchEnrollments(); // Refresh the enrollments list after approving
    });
  }

  rejectEnrollment(enrollmentId: number): void {
    this.instructorService.rejectEnrollment(enrollmentId).subscribe(() => {
      this.fetchEnrollments(); // Refresh the enrollments list after rejecting
    });
  }

  selectTab(tab: string): void {
    this.selectedTab = tab;
  }

  getFilteredEnrollments(status: string): Enrollment[] {
    return this.enrollments.filter(enrollment => enrollment.status.toLowerCase() === status);
  }
}
