package com.example.demo.student.controller;

import com.example.demo.student.service.StudentService;
import com.example.demo.student.model.Student;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ApiOperation(
            value = "Finds all students",
            notes = "Returns the list of all students",
            response = Student.class)
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    @ApiOperation(
            value = "Add new student",
            notes = "Inserts a new student into the student's list",
            response = Student.class)
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    @ApiOperation(
            value = "Delete a student",
            notes = "Removes a student by id from the student's list",
            response = Student.class)
    public void deleteStudent(@ApiParam(value = "ID value for the student you want to delete", required = true, example = "123")
            @PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    @ApiOperation(
            value = "Update a student's param",
            notes = "Updates a student's parameter",
            response = Student.class)
    public void updateStudent(
            @ApiParam(value = "ID value for the student you want to update", required = true, example = "123")
            @PathVariable("studentId") Long studentId,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }
}
