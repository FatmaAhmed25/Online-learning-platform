import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';


import { ReactiveFormsModule } from '@angular/forms';
import { StudentComponent } from './student/student.component';
import { InstructorComponent } from './instructor/instructor.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AdminComponent } from './admin/admin.component';
import { RegsiterinstructorComponent } from './regsiterinstructor/regsiterinstructor.component';
import { AllCoursesComponent } from './all-courses/all-courses.component';
import { StudentEnrollmentsComponent } from './student-enrollments/student-enrollments.component';
import { CreateCourseComponent } from './create-course/create-course.component';
import { UsersComponent } from './users/users.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    StudentComponent,
    InstructorComponent,
    HomepageComponent,
    AdminComponent,
    RegsiterinstructorComponent,
    AllCoursesComponent,
    StudentEnrollmentsComponent,
    CreateCourseComponent,
    UsersComponent
  ],
  imports: [
    BrowserAnimationsModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
