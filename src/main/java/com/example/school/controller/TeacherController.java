package com.example.school.controller;

import com.example.school.entity.Teacher;
import com.example.school.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PostMapping("/add")
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }
    @DeleteMapping("/delete/{Id}")
    public String deleteTeacher(@PathVariable int Id) {
        return teacherService.deleteTeacher(Id);
    }
    @PutMapping("/update/{Id}")
    public Teacher updateTeacher(@PathVariable int Id, @RequestBody Teacher teacher) {
        return teacherService.updateTeacher(Id, teacher);
    }
    @GetMapping("/get")
    public List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }
}
