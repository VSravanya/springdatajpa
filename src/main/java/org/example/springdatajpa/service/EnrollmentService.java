package org.example.springdatajpa.service;

import org.example.springdatajpa.dto.StudentDTO;
import org.example.springdatajpa.entity.Enrollment;
import org.example.springdatajpa.entity.keys.EnrollmentKey;
import org.example.springdatajpa.entity.response.StudentEnrollments;

import java.util.List;

public interface EnrollmentService {
    public Enrollment getEnrollmentById(EnrollmentKey id);
    public List<Enrollment> getAllEnrollments();
    public Enrollment createEnrollment(Enrollment enrollment);
    public Enrollment updateEnrollment(EnrollmentKey id, Enrollment enrollment);
    public void deleteEnrollment(EnrollmentKey id);

    public StudentEnrollments getStudentEnrollments(String studentId);
}
