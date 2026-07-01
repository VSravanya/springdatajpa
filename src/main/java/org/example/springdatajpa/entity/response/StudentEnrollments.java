package org.example.springdatajpa.entity.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springdatajpa.entity.Course;
import org.example.springdatajpa.entity.Enrollment;

import java.util.List;
import java.util.Map;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollments {
    private String studentId;
    private String firstName;
    private String lastName;
    private List<CourseDTO> enrollments;

}
