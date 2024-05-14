// login.component.ts
import { Component } from '@angular/core';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string='';
  password: string='';

  constructor(private loginService: LoginService, private router: Router) { }

  login() {
    this. loginService.login(this.email, this.password).subscribe(
      response => {
        console.log('Login successful:', response);
        console.log(response.id)
        const user_id=response.id
        localStorage.setItem('userId', user_id);
        const userRole = response.role; // Assuming you retrieve the user's role from the response
        if (userRole === 'STUDENT') {
          this.router.navigate(['/student/' + user_id]);
        } else if (userRole === 'INSTRUCTOR') {
          this.router.navigate(['/instructor/' + user_id]);
        }
        // Redirect to dashboard or home page
        //this.router.navigate(['/dashboard']);
      },
      error => {
        console.error('Error logging in:', error);
        // Handle error, such as displaying an error message to the user
      }
    );
  }
}
