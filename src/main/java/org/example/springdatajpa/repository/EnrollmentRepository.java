package org.example.springdatajpa.repository;

import org.example.springdatajpa.entity.Enrollment;
import org.example.springdatajpa.entity.keys.EnrollmentKey;
import org.example.springdatajpa.entity.request.EnrollmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey> {

    @Query("SELECT new org.example.springdatajpa.entity.request.EnrollmentDTO(" +
            "  s.id, s.firstName, s.lastName, c.courseName, c.credits, e.enrollmentDate" +
            ") " +
            "FROM Enrollment e " +
            "JOIN e.student s " +     // navigates Enrollment.student (@ManyToOne)
            "JOIN e.course c " +      // navigates Enrollment.course (@ManyToOne)
            "WHERE s.id = :studentId")
    public List<EnrollmentDTO> getStudentEnrollments(@Param("studentId") String studentId);
}
