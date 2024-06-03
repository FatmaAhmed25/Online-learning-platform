# Online Learning Application

This is an online learning application implemented using Enterprise JavaBeans (EJBs) and microservices. The application allows admins, instructors, and students to interact with the platform according to their roles and responsibilities.

## Features

### Admins

- View and manage user accounts.
- Review course content before publishing.
- Edit or remove courses violating policies.
- Track platform usage by students and instructors.

### Instructors

- Register and login.
- Create courses with details.
- View detailed course information.
- Accept/reject student enrollments.

### Students

- Register and login.
- View course enrollments.
- View detailed course information.
- Make or cancel course enrollments.
- Receive enrollment updates.
- Make reviews and ratings for courses.

## Project Structure

The project consists of the following components:

1. **Service 1 (EJBs)**: Handles user management functionalties
2. **Service 2 (Spring Boot)**: Manages course content review, tracking platform usage, and notifications.
3. **Frontend (Angular)**: Provides a user interface to interact with the application.

## Technologies Used

- Java EE
- Enterprise JavaBeans (EJBs)
- Spring Boot (for Service 2)
- Angular (for the frontend)
- RESTful APIs
