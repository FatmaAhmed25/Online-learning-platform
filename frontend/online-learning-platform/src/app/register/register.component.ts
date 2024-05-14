import { Component } from '@angular/core';
import { RegisterService } from '../services/register.service';
import { User } from '../models/user.model';
import { UserRole } from '../models/userRole';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User = { name: '', email: '', password: '', role: UserRole.STUDENT }; // Initialize user object

  constructor(private registerService: RegisterService ,private router: Router) { }

  registerUser() {
   
    console.log(this.user);
    if (this.user.role === 'STUDENT') {
      this.registerService.registerStudent(this.user).subscribe(
        response => {
          
          console.log(this.user);
          console.log('Student registered successfully:', response);
          // Handle success, such as showing a success message or redirecting to another page
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error registering student:', error);
          // Handle error, such as displaying an error message to the user
        }
      );
    } 
    else if (this.user.role === 'INSTRUCTOR') {
      this.registerService.registerInstructor(this.user).subscribe(
        response => {
          console.log('Instructor registered successfully:', response);
          // Handle success, such as showing a success message or redirecting to another page
        },
        error => {
          console.error('Error registering instructor:', error);
          // Handle error, such as displaying an error message to the user
        }
      );
    } else {
      console.error('Invalid user role:', this.user.role);
      // Handle invalid user role, such as displaying an error message to the user
    }
  }
}
