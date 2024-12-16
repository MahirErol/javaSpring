package com.example.school.controller;

import com.example.school.entity.Lesson;

import com.example.school.service.LessonService;

import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
public class LessonController {
    private final LessonService lessonService;
    private final StudentService studentService;

    @PostMapping("/add")
    public Lesson addLesson(@RequestBody Lesson lesson) {
        return lessonService.addLesson(lesson);
    }
    @DeleteMapping("/delete/{Id}")
    public String deleteLesson(@PathVariable int Id) {
        return lessonService.deleteLesson(Id);
    }
}