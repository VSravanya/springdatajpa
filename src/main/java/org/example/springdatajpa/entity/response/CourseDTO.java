package org.example.springdatajpa.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springdatajpa.entity.Term;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private String courseId;
    private String courseName;
    private int credit;
    private LocalDate enrolledDate;
    private Term termOffered;
    private String instructor;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer maxCapacity;
    private boolean active;
}
