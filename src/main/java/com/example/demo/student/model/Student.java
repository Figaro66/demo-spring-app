package com.example.demo.student.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
@Data
@NoArgsConstructor
@ApiModel(description = "Details about the students")
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    @ApiModelProperty(value = "The unique id of a student")
    Long id;
    @ApiModelProperty(value = "The student's name")
    String name;
    @ApiModelProperty(value = "The student's email")
    String email;
    @ApiModelProperty(value = "The student's date of birth in format yyyy-MM-dd")
    LocalDate dob;
    @ApiModelProperty(value = "The student's age")
    @Transient
    Integer age;

    public Student(Long id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Student(String name, String email, LocalDate dob) {
        this.name = name;
        this.email = email;
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

}
