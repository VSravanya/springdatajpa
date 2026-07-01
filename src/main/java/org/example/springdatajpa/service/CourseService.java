package org.example.springdatajpa.service;

import org.example.springdatajpa.dto.StudentDTO;
import org.example.springdatajpa.entity.Course;
import org.example.springdatajpa.entity.response.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourseService {

    public CourseDTO getCourseByID(String id);
    public Page<CourseDTO> getAllCourses(Pageable pageable);
    public Slice<CourseDTO> getAllCoursesBy(Pageable pageable);
    public Page<CourseDTO> searchCourses(Specification<Course> spec, Pageable pageable);
    public CourseDTO createCourse(CourseDTO course);
    public CourseDTO updateCourse(String id,CourseDTO course);
    public void deleteCourse(String id);

}
