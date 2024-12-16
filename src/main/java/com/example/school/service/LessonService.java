package com.example.school.service;
import com.example.school.entity.Lesson;
import com.example.school.repository.LessonRepository;

import org.springframework.stereotype.Service;


@Service

public class LessonService {
    private final LessonRepository lessonRepository;
    public LessonService (LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

      public Lesson addLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
      }
      public String deleteLesson(int id) {
        Lesson lesson = lessonRepository.findById(id).get();
        lessonRepository.delete(lesson);
          return "Lesson deleted";
      }


}
