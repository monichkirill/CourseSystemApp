package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.EnrollmentCreateDto;
import org.example.dto.EnrollmentDto;
import org.example.dto.UserDto;
import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;
import org.example.security.UserDetailsImpl;
import org.example.service.CourseService;
import org.example.service.EnrollmentService;
import org.example.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
    }

    @PreAuthorize("hasRole('STUDENT')")
    @PostMapping
    public ResponseEntity<EnrollmentDto> enroll(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @Valid @RequestBody EnrollmentCreateDto dto
    ) {
        User student = userDetails.getUser();
        Course course = courseService.getCourseById(dto.getCourseId());
        Enrollment enrollment = enrollmentService.enroll(student, course);
        return ResponseEntity.ok(Mapper.toEnrollmentDto(enrollment));
    }

    @PreAuthorize("hasRole('STUDENT')")
    @GetMapping
    public ResponseEntity<List<EnrollmentDto>> getEnrollments(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        User student = userDetails.getUser();
        List<EnrollmentDto> result = enrollmentService.getEnrollmentsByStudent(student)
                .stream()
                .map(Mapper::toEnrollmentDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('TEACHER', 'ADMIN')")
    @GetMapping("/course/{courseId}/students")
    public ResponseEntity<List<UserDto>> getStudentsByCourse(
            @PathVariable Long courseId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Course course = courseService.getCourseById(courseId);

        User user = userDetails.getUser();

        if (user.getRole().name().equals("TEACHER") &&
                !course.getTeacher().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build(); // Доступ запрещён
        }

        List<UserDto> students = enrollmentService.getEnrollmentsByCourse(course).stream()
                .map(enrollment -> Mapper.toUserDto(enrollment.getStudent()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(students);
    }

}