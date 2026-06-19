package org.example.springdatajpa.service.impl;

import org.example.springdatajpa.dto.StudentDTO;
import org.example.springdatajpa.entity.Student;
import org.example.springdatajpa.repository.StudentRepository;
import org.example.springdatajpa.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public StudentDTO getStudentById(String id) {
        return studentRepository.findById(id)
                .map(this::toStudentDTO)
                .orElse(null);
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toStudentDTO)
                .toList();
    }

    @Override
    public StudentDTO createStudent(StudentDTO student) {
        Student studentEntity = toStudent(student);
        studentEntity.setId(UUID.randomUUID().toString()); // Explicitly set a new ID for creation
        Student savedStudent = studentRepository.save(studentEntity);
        return toStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(String id, StudentDTO student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    Student studentToUpdate = toStudent(student);
                    // Ensure the ID from the path is used, not from the body
                    studentToUpdate.setId(id);
                    Student updatedStudent = studentRepository.save(studentToUpdate);
                    return toStudentDTO(updatedStudent);
                })
                .orElse(null); // Or throw a custom exception
    }

    @Override
    public void deleteStudent(String id) {
        if (!studentRepository.existsById(id)) {
            // In a real app, you might throw a ResourceNotFoundException here
            return;
        }
        studentRepository.deleteById(id);
    }

    public Student toStudent(StudentDTO studentDTO){
        return Student.builder()
                // ID is now handled by the create/update methods, not the mapper.
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .build();
    }

    public StudentDTO toStudentDTO(Student student){
        return StudentDTO.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .build();
    }
}
