package org.example.springdatajpa.service.impl;

import jakarta.transaction.Transactional;
import org.example.springdatajpa.entity.Course;
import org.example.springdatajpa.entity.response.CourseDTO;
import org.example.springdatajpa.repository.CourseRepository;
import org.example.springdatajpa.service.CourseService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    public CourseServiceImpl(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    @Override
//    @Cacheable(value = "course", key = "#id")
    public CourseDTO getCourseByID(String id) {
        return courseRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Override
    public Page<CourseDTO> getAllCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(this::toDTO);
    }
    @Override
    public Slice<CourseDTO> getAllCoursesBy(Pageable pageable) {
        return courseRepository.findAllBy(pageable)
                .map(this::toDTO);
    }

    @Override
    public Page<CourseDTO> searchCourses(Specification<Course> spec, Pageable pageable) {
        return courseRepository.findAll(spec,pageable)
                .map(this::toDTO);
    }



    @Override
    public CourseDTO createCourse(CourseDTO course) {
        CourseDTO preparedCourse = course.toBuilder()
                .courseId(UUID.randomUUID().toString())
                .build();

        Course savedEntity = courseRepository.save(this.toEntity(preparedCourse));
        return this.toDTO(savedEntity);
    }

    @Override
    @Transactional
    public CourseDTO updateCourse(String id, CourseDTO course) {
        return courseRepository.findById(id)
                .map(existingCourse ->{
                    // Map DTO to an entity, ensuring the correct ID is set
                    Course entityToUpdate = toEntity(course);
                    entityToUpdate.setCourseId(id);
                    // Save the updated entity
                    Course updatedEntity = courseRepository.save(entityToUpdate);
                    // Map the *saved* entity back to a DTO for the response
                    return toDTO(updatedEntity);
                })
                .orElse(null);
    }

    @Override
    public void deleteCourse(String id) {
        // It's good practice to check for existence before deleting
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
        }
        // In a real app, you might throw a ResourceNotFoundException if it doesn't exist
    }

    private CourseDTO toDTO(Course course){
        return CourseDTO.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .credit(course.getCredits())
                .termOffered(course.getTermOffered())
                .instructor(course.getInstructor())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .maxCapacity(course.getMaxCapacity())
                .active(course.isActive())
                .build();
    }
    private Course toEntity(CourseDTO course) {
        return Course.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .credits(course.getCredit())
                .termOffered(course.getTermOffered())
                .instructor(course.getInstructor())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .maxCapacity(course.getMaxCapacity())
                .active(course.isActive())
                .build();
    }
}
