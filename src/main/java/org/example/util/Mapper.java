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
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword()) // не забудь шифровать в сервисе
                .role(dto.getRole())
                .build();
    }

    public static Course toCourse(CourseCreateDto dto) {
        return Course.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public static CourseDto toCourseDto(Course course) {
        return CourseDto.builder()
                .id(course.getId())
                .title(course.getTitle())
                .description(course.getDescription())
                .teacherUsername(course.getTeacher().getUsername())
                .createdAt(course.getCreatedAt().format(formatter))
                .build();
    }

    public static EnrollmentDto toEnrollmentDto(Enrollment enrollment) {
        return EnrollmentDto.builder()
                .courseId(enrollment.getCourse().getId())
                .courseTitle(enrollment.getCourse().getTitle())
                .enrolledAt(enrollment.getEnrolledAt().format(formatter))
                .build();
    }
}
