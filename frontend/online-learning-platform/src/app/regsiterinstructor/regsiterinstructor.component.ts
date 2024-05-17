import { Component } from '@angular/core';
import { User } from '../models/user.model';
import { UserRole } from '../models/userRole';
import { RegisterService } from '../services/register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-regsiterinstructor',
  templateUrl: './regsiterinstructor.component.html',
  styleUrls: ['./regsiterinstructor.component.css']
})
export class RegsiterinstructorComponent {
  user: User = { name: '', email: '', password: '',affiliation:'',bio:'',yearsOfExperience:'', role: UserRole.INSTRUCTOR }; // Initialize user object

  constructor(private registerService: RegisterService ,private router: Router) { }

  registerInstructor() {
   
    console.log(this.user);
      this.registerService.registerInstructor(this.user).subscribe(
        response => {
          
          console.log(this.user);
          console.log('Instructor registered successfully:', response);
          this.router.navigate(['/login']);
        },
        error => {
          console.error('Error registering instructor:', error);
        }
      );
    
  }
}
