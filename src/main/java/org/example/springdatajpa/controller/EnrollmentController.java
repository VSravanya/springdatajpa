package org.example.springdatajpa.controller;

import org.example.springdatajpa.entity.Enrollment;
import org.example.springdatajpa.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    public EnrollmentController(EnrollmentService enrollmentService){
        this.enrollmentService=enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> postEnrollment(@RequestBody Enrollment enrollment){
        return ResponseEntity.ok(enrollmentService.createEnrollment(enrollment));
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments(){
        return  ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

}
