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
  user: User = { name: '', email: '', password: '',affiliation:'',bio:'', role: UserRole.STUDENT }; // Initialize user object

  constructor(private registerService: RegisterService ,private router: Router) { }

  registerStudent() {
   
    console.log(this.user);
      this.registerService.registerStudent(this.user).subscribe(
        response => {
          
          console.log(this.user);
          console.log('Student registered successfully:', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error registering student:', error);
        }
      );
    
  }
}
