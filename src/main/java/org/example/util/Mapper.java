package org.example.util;

import org.example.dto.*;
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
        String createdAtStr = course.getCreatedAt() != null ? course.getCreatedAt().toString() : null;
        String teacherUsername = course.getTeacher() != null ? course.getTeacher().getUsername() : null;
        return new CourseDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                teacherUsername,
                createdAtStr
        );
    }

    public static EnrollmentDto toEnrollmentDto(Enrollment enrollment) {
        EnrollmentDto dto = new EnrollmentDto();
        dto.setCourseId(enrollment.getCourse() != null ? enrollment.getCourse().getId() : null);
        dto.setCourseTitle(enrollment.getCourse() != null ? enrollment.getCourse().getTitle() : null);
        dto.setEnrolledAt(enrollment.getEnrolledAt() != null ? enrollment.getEnrolledAt().format(formatter) : null);
        return dto;
    }

    public static UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
