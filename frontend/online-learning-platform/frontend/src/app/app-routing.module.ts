import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { StudentComponent } from './student/student.component';
import { InstructorComponent } from './instructor/instructor.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminComponent } from './admin/admin.component';
import { RegsiterinstructorComponent } from './regsiterinstructor/regsiterinstructor.component';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { StudentEnrollmentsComponent } from './student-enrollments/student-enrollments.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: 'registerstudent', component: RegisterComponent },
  { path: '', component: HomepageComponent },
  { path: 'registerstudent', component: RegisterComponent },
  { path: 'registerinstructor', component: RegsiterinstructorComponent },
  { path: 'login', component: LoginComponent },
  { path: 'admin/:id', component: AdminComponent },
  { path: 'student/:id', component: StudentComponent },
  { path: 'instructor/:id', component: InstructorComponent },
  { path: 'AllCourses/:id', component: AllCoursesComponent },
  { path: 'studentEnrollments/:id', component: StudentEnrollmentsComponent},
  { path: 'create-course', component: CreateCourseComponent },
  { path: 'users', component: UsersComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
