package org.example.springdatajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Student {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    private List<Enrollment> enrollmentList;
}
