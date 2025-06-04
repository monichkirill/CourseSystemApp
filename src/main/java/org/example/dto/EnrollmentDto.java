package org.example.dto;

public class EnrollmentDto {
    private Long courseId;
    private String courseTitle;
    private String enrolledAt;

    public EnrollmentDto() {}

    public EnrollmentDto(Long courseId, String courseTitle, String enrolledAt) {
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrolledAt = enrolledAt;
    }

    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseTitle() { return courseTitle; }
    public void setCourseTitle(String courseTitle) { this.courseTitle = courseTitle; }

    public String getEnrolledAt() { return enrolledAt; }
    public void setEnrolledAt(String enrolledAt) { this.enrolledAt = enrolledAt; }
}
