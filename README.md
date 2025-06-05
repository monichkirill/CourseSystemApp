# Course Management System

![Java](https://img.shields.io/badge/Java-17-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue.svg)

A simple web-based course management system built with Java, Spring Boot, Spring Security, and PostgreSQL. It allows teachers to create courses, students to enroll in them, and both to view course-related data through a secure REST API.

## Features

### Authentication & Authorization
- User registration and login with JWT
- Role-based access control: `STUDENT`, `TEACHER`, `ADMIN`

### Courses
- Teachers can create new courses
- Courses have title, description, and creation timestamp
- Students can view available courses

### Enrollments
- Students can enroll in a course only once
- Students can view the list of their enrollments
- Teachers can view the list of students enrolled in their courses

### Security
- JWT-based authentication
- Stateless API with Spring Security
- Route protection using `@PreAuthorize`

## Technologies Used
- Java 17+
- Spring Boot 3+
- Spring Security (JWT)
- Spring Data JPA + Hibernate
- PostgreSQL
- Maven

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL

## API Endpoints

### Auth
- `POST /api/auth/register` — Register a new user (public)
- `POST /api/auth/login` — Login and receive JWT (public)

### Courses
- `GET /api/courses` — Get all courses (any authenticated user)
- `POST /api/courses` — Create a course (TEACHER only)
- `DELETE /api/courses/{id}` — Delete course (TEACHER or ADMIN only)

### Enrollments
- `POST /api/enrollments` — Enroll in a course (STUDENT only)
- `GET /api/enrollments` — Get your enrollments (STUDENT only)
- `GET /api/enrollments/course/{courseId}/students` — Get students enrolled in a course (TEACHER only)
