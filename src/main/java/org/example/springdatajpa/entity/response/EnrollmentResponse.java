package org.example.springdatajpa.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class EnrollmentResponse {
    private String studentId;
    private String studentName;
    private String courseId;
    private String courseName;
    private LocalDate enrolledDate;

    public String getStudentName(String firstName, String lastName){
        return String.join(" ",firstName,lastName);
    }
}
