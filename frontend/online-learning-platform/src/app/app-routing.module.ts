import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { StudentComponent } from './student/student.component';
import { InstructorComponent } from './instructor/instructor.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminComponent } from './admin/admin.component';
import { RegsiterinstructorComponent } from './regsiterinstructor/regsiterinstructor.component';

const routes: Routes = [
  { path: 'register', component: RegisterComponent },
  { path: '', component: HomepageComponent },
  { path: 'registerstudent', component: RegisterComponent },
  { path: 'registerinstructor', component: RegsiterinstructorComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin/:id', component: AdminComponent },
  { path: 'student/:id', component: StudentComponent },
  { path: 'instructor/:id', component: InstructorComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
