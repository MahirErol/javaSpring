package com.example.school.service;

import com.example.school.entity.Teacher;
import com.example.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();

    }
    public String deleteTeacher(int id) {
        Teacher teacher = teacherRepository.findById(id).get();
        teacherRepository.delete(teacher);
        return "Teacher deleted";
    }

    public Teacher updateTeacher(@PathVariable("id") int id, @RequestBody Teacher teacher) {
        Teacher current = teacherRepository.findById(id).get();
        current.setName(teacher.getName());


        return current;
    }
}
