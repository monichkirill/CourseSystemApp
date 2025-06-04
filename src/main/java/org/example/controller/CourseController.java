package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.CourseCreateDto;
import org.example.dto.CourseDto;
import org.example.entity.Course;
import org.example.entity.User;
import org.example.security.UserDetailsImpl;
import org.example.security.UserDetailsServiceImpl;
import org.example.service.CourseService;
import org.example.service.UserService;
import org.example.util.Mapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<CourseDto> create(@RequestBody @Valid CourseCreateDto dto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User teacher = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseService.createCourse(Mapper.toCourse(dto), teacher);
        return ResponseEntity.ok(Mapper.toCourseDto(course));
    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAll() {
        List<CourseDto> list = courseService.getAllCourses().stream()
                .map(Mapper::toCourseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getById(@PathVariable Long id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(Mapper.toCourseDto(course));
    }
}
