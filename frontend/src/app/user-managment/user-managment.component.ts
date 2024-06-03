import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Instructor, Student } from '../models/users.model';
import { UserRole } from '../models/userRole';

@Component({
  selector: 'app-user-managment',
  templateUrl: './user-managment.component.html',
  styleUrls: ['./user-managment.component.css']
})
export class UserManagmentComponent implements OnInit {
  students: Student[] = [];
  instructors: Instructor[] = [];


  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getAllStudents().subscribe(students => {
      this.students = students;
    });

    this.userService.getAllInstructors().subscribe(instructors => {
      this.instructors = instructors;
    });
  }
  getAllUsers(): void {
    this.userService.getAllStudents().subscribe(students => {
      this.students = students;
    });

    this.userService.getAllInstructors().subscribe(instructors => {
      this.instructors = instructors;
    });
  }



  editStudent(student: Student): void {
    this.userService.editStudent(student).subscribe(
      () => {
        console.log('Student updated successfully');
        this.getAllUsers();

      },
      error => {
        console.error('Error updating student:', error);
     
      }
    );
  }

  editInstructor(instructor: Instructor): void {
    this.userService.editInstructor(instructor).subscribe(
      () => {
        console.log('Instructor updated successfully');

      },
      error => {
        console.error('Error updating instructor:', error);

      }
    );
  }
  deleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(
      () => {
        console.log('User deleted successfully');
        this.getAllUsers();

      },
      error => {
        console.error('Error deleting user:', error);
     
      }
    );
  }
}
