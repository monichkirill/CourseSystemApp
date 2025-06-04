package org.example.dto;

public class CourseDto {
    private Long id;
    private String title;
    private String description;
    private String teacherUsername;
    private String createdAt;

    public CourseDto() {}

    public CourseDto(Long id, String title, String description, String teacherUsername, String createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherUsername = teacherUsername;
        this.createdAt = createdAt;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTeacherUsername() { return teacherUsername; }
    public void setTeacherUsername(String teacherUsername) { this.teacherUsername = teacherUsername; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
