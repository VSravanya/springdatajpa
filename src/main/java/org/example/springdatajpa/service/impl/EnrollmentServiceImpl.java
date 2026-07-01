package org.example.springdatajpa.service.impl;

import jakarta.transaction.Transactional;
import org.example.springdatajpa.entity.Course;
import org.example.springdatajpa.entity.Enrollment;
import org.example.springdatajpa.entity.Student;
import org.example.springdatajpa.entity.keys.EnrollmentKey;
import org.example.springdatajpa.entity.request.EnrollmentDTO;
import org.example.springdatajpa.entity.response.CourseDTO;
import org.example.springdatajpa.entity.response.StudentEnrollments;
import org.example.springdatajpa.repository.CourseRepository;
import org.example.springdatajpa.repository.EnrollmentRepository;
import org.example.springdatajpa.repository.StudentRepository;
import org.example.springdatajpa.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            StudentRepository studentRepository,
            CourseRepository courseRepository
    ){
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    @Override
    public Enrollment getEnrollmentById(EnrollmentKey id) {
        return null;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {

        return enrollmentRepository.findAll();
    }

    @Override
    @Transactional
    public Enrollment createEnrollment(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getEnrollmentId().getStudentId())
                .orElseThrow();
        Course course = courseRepository.findById(enrollment.getEnrollmentId().getCourseId())
                .orElseThrow();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollmentRepository.save(enrollment);

//        if(true){
//            throw new RuntimeException("Error");
//        }
        student.setFirstName("changed name");
        return enrollment;

    }

    @Override
    public Enrollment updateEnrollment(EnrollmentKey id, Enrollment enrollment) {
        return null;
    }

    @Override
    public StudentEnrollments getStudentEnrollments(String studentId) {
        List<EnrollmentDTO> enrollmentDTOS = enrollmentRepository.getStudentEnrollments(studentId);
        StudentEnrollments result = StudentEnrollments.builder()
                .studentId(studentId)
                .firstName(enrollmentDTOS.get(0).getFirstName())
                .lastName(enrollmentDTOS.get(0).getLastName())
                .build();
        List<CourseDTO> courseList = new ArrayList<>();
        for (EnrollmentDTO enrollmentDTO: enrollmentDTOS){
            courseList.add(CourseDTO.builder().courseName(enrollmentDTO.getCourseName())
                    .enrolledDate(enrollmentDTO.getEnrollmentDate())
                    .credit(enrollmentDTO.getCredits())
                    .build());

        }
        result.setEnrollments(courseList);
        return result;
    }

    @Override
    public void deleteEnrollment(EnrollmentKey id) {

    }
}
