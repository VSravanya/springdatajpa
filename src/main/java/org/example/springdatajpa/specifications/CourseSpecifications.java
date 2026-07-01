package org.example.springdatajpa.specifications;

import org.example.springdatajpa.entity.Course;
import org.springframework.data.jpa.domain.Specification;

public class CourseSpecifications {
    public static Specification<Course> hasName(String name){
        return (root, query, cb ) ->
                name == null ? null : cb.like(root.get("courseName"), "%"+ name +"%");

    }

    public static Specification<Course> isActive(Boolean active){
//        if (active == null){
//            return null;
//        }
        return (root,query,cb) ->
                active == null ? null : cb.equal(root.get("active"),active);
    }

    public static Specification<Course> hasMaxCredits(Integer credits){
//        if(credits == null){
//            return null;
//        }
        return (root,query,cb) ->
                credits == null ? null : cb.lessThanOrEqualTo(root.get("credits"),credits);
    }

    public static Specification<Course> hasInstructor(String instructor){
//        if(instructor== null){
//            return null;
//        }
        return (root,query,cb)->
                instructor == null ? null : cb.like(root.get("instructor"),"%"+instructor+"%");
    }
}
