package org.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }

    public User() {
    }

    public User(Long id, String username, String email, String password, Role role,
                List<Course> courses, List<Enrollment> enrollments) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.courses = courses;
        this.enrollments = enrollments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public static class Builder {
        private Long id;
        private String username;
        private String email;
        private String password;
        private Role role;
        private List<Course> courses;
        private List<Enrollment> enrollments;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder courses(List<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Builder enrollments(List<Enrollment> enrollments) {
            this.enrollments = enrollments;
            return this;
        }

        public User build() {
            return new User(id, username, email, password, role, courses, enrollments);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
