package org.example.springdatajpa.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String courseName;
    private int credits;
    private LocalDate enrollmentDate;
}