package org.example.springdatajpa.repository;

import org.example.springdatajpa.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,String>, JpaSpecificationExecutor<Course> {

    Slice<Course> findAllBy(Pageable pageable);
}
