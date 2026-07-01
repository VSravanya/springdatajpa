package org.example.springdatajpa.controller;

import org.example.springdatajpa.entity.Course;
import org.example.springdatajpa.entity.response.CourseDTO;
import org.example.springdatajpa.service.CourseService;
import org.example.springdatajpa.specifications.CourseSpecifications;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourse(@PathVariable String courseId){
        CourseDTO course = courseService.getCourseByID(courseId);
        if (course!= null){
            return ResponseEntity.ok(course);
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<CourseDTO>> getAllCourses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "columnName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,sortBy));

        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/paginated/bySlice")
    public ResponseEntity<Slice<CourseDTO>> getAllCoursesBy(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "columnName") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder
    ){
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,sortBy));

        return ResponseEntity.ok(courseService.getAllCoursesBy(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<CourseDTO>> searchAllCourses(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "columnName") String sortBy,
        @RequestParam(defaultValue = "asc") String sortOrder,
        @RequestParam(required = false) String courseName,
        @RequestParam(required = false) Integer credits,
        @RequestParam(required = false) String instructor
        ){

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc")
                ?Sort.Direction.DESC
                :Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page,size,Sort.by(direction,sortBy));

        Specification<Course> spec = Specification.where(CourseSpecifications.hasName(courseName))
                .and(CourseSpecifications.hasMaxCredits(credits));
//                .and(CourseSpecifications.hasInstructor(instructor));
        return ResponseEntity.ok(courseService.searchCourses(spec,pageable));
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO course){
        return ResponseEntity.ok(courseService.createCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String courseId, @RequestBody CourseDTO course){
        return ResponseEntity.ok(courseService.updateCourse(courseId,course));
    }




}
