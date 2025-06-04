package org.example.util;

import org.example.dto.CourseCreateDto;
import org.example.dto.CourseDto;
import org.example.dto.EnrollmentDto;
import org.example.dto.UserRegisterDto;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;

import java.time.format.DateTimeFormatter;

public class Mapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static User toUser(UserRegisterDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // не забудь шифровать в сервисе
        user.setRole(dto.getRole());
        return user;
    }

    public static Course toCourse(CourseCreateDto dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        return course;
    }

    public static CourseDto toCourseDto(Course course) {
        CourseDto dto = new CourseDto();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setTeacherUsername(course.getTeacher() != null ? course.getTeacher().getUsername() : null);
        dto.setCreatedAt(course.getCreatedAt() != null ? course.getCreatedAt().format(formatter) : null);
        return dto;
    }

    public static EnrollmentDto toEnrollmentDto(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setCourseId(enrollment.getCourse() != null ? enrollment.getCourse().getId() : null);
        dto.setCourseTitle(enrollment.getCourse() != null ? enrollment.getCourse().getTitle() : null);
        dto.setEnrolledAt(enrollment.getEnrolledAt() != null ? enrollment.getEnrolledAt().format(formatter) : null);
        return dto;
    }
}
