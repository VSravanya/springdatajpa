package org.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.springdatajpa.entity.keys.EnrollmentKey;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Enrollment {

    @EmbeddedId
    private EnrollmentKey enrollmentId;

    private LocalDate enrollmentDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    @MapsId("studentId")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    @MapsId("courseId")
    @ToString.Exclude
    private Course course;


}