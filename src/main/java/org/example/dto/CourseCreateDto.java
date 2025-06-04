package org.example.dto;

import jakarta.validation.constraints.NotBlank;

public class CourseCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    public CourseCreateDto() {}

    public CourseCreateDto(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
