package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CourseCreateDto;
import org.example.dto.CourseDto;
import org.example.entity.Course;
import org.example.entity.User;
import org.example.security.UserDetailsImpl;
import org.example.service.CourseService;
import org.example.service.UserService;
import org.example.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseCreateDto dto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Course course = Mapper.toCourse(dto);
        User teacher = userDetails.getUser();
        Course created = courseService.createCourse(course, teacher);
        return ResponseEntity.ok(Mapper.toCourseDto(created));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        List<CourseDto> courses = courseService.getAllCourses().stream()
                .map(Mapper::toCourseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(courses);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseDto> update(@PathVariable Long id, @Valid @RequestBody CourseCreateDto dto) {
        Course existingCourse = courseService.getCourseById(id);
        existingCourse.setTitle(dto.getTitle());
        existingCourse.setDescription(dto.getDescription());
        Course updated = courseService.createCourse(existingCourse, existingCourse.getTeacher()); // или вызвать updateCourse
        return ResponseEntity.ok(Mapper.toCourseDto(updated));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}