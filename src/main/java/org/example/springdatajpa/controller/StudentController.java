package org.example.springdatajpa.controller;

import org.example.springdatajpa.dto.StudentDTO;
import org.example.springdatajpa.entity.Enrollment;
import org.example.springdatajpa.entity.Student;
import org.example.springdatajpa.entity.keys.EnrollmentKey;
import org.example.springdatajpa.entity.request.EnrollmentRequest;
import org.example.springdatajpa.entity.response.EnrollmentResponse;
import org.example.springdatajpa.entity.response.StudentEnrollments;
import org.example.springdatajpa.service.EnrollmentService;
import org.example.springdatajpa.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    public StudentController(
            StudentService studentService,
            EnrollmentService enrollmentService
    ){
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id){
        StudentDTO student = studentService.getStudentById(id);
        if(student != null){
            return ResponseEntity.ok(student);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }


    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO student){
        StudentDTO createdStudent = studentService.createStudent(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdStudent.getId()).toUri();
        return ResponseEntity.created(location).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studentId}/enroll")
    public ResponseEntity<EnrollmentResponse> enrollInCourse(
            @PathVariable String studentId,
            @RequestBody EnrollmentRequest request){
        Enrollment prepareEnrollment = Enrollment.builder()
                .enrollmentId(
                        EnrollmentKey.builder()
                                .studentId(studentId)
                                .courseId(request.getCourseId())
                        .build()
                )
                .enrollmentDate(LocalDate.now())
                .build();
        Enrollment savedEnrollment = enrollmentService.createEnrollment(prepareEnrollment);
        EnrollmentResponse response = EnrollmentResponse
                .builder()
                .studentId(savedEnrollment.getStudent().getId())
                .studentName(savedEnrollment.getStudent().getFirstName())
                .courseId(savedEnrollment.getCourse().getCourseId())
                .enrolledDate(savedEnrollment.getEnrollmentDate())
                .build();
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{studentId}/enrollments")
    public ResponseEntity<StudentEnrollments> studentEnrollments(
            @PathVariable String studentId
    ){
        return ResponseEntity.ok(enrollmentService.getStudentEnrollments(studentId));
    }
}
