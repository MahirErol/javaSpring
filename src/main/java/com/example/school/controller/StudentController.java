package com.example.school.controller;

import com.example.school.entity.Student;
import com.example.school.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @DeleteMapping("/delete/{Id}")
    public String delete(@PathVariable int Id) {
        return studentService.deleteStudent(Id);
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);

    }

    @GetMapping("/get")
    public List<Student> getStudents() {
        return studentService.getAllStudents();
    }

    @PutMapping("/update/{Id}")
    public Student updateStudent(@PathVariable int Id, @RequestBody Student student) {
        return studentService.updateStudent(Id,student);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }
}

