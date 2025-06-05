package org.example.dto;

import jakarta.validation.constraints.NotNull;

public class EnrollmentCreateDto {

    @NotNull
    private Long courseId;

    public EnrollmentCreateDto() {}

    public EnrollmentCreateDto(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
