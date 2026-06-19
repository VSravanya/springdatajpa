package org.example.springdatajpa.service;

import org.example.springdatajpa.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    public StudentDTO getStudentById(String id);
    public List<StudentDTO> getAllStudents();
    public StudentDTO createStudent(StudentDTO student);
    public StudentDTO updateStudent(String id,StudentDTO student);
    public void deleteStudent(String id);
    // batch update
    // batch delete
    // batch insert

}
