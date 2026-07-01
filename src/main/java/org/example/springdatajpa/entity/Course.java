package org.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Course {
    @Id
    private String courseId;
    @NonNull
    private String courseName;
    private int credits;
    @Enumerated(EnumType.STRING)
    private Term termOffered;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxCapacity;
    private boolean active;

    @OneToMany(mappedBy = "course")
    @ToString.Exclude
    private List<Enrollment> enrollmentList;
}
