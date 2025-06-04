package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    // Преподаватель → курсы
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    // Студент → записи
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    public enum Role {
        STUDENT, TEACHER, ADMIN
    }
}
