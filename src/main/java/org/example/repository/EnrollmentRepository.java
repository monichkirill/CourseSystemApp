package org.example.repository;

import org.example.entity.Course;
import org.example.entity.Enrollment;
import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(User student);
    List<Enrollment> findByCourse(Course course);
    boolean existsByStudentAndCourse(User student, Course course);
}
