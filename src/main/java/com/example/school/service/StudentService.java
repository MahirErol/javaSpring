package com.example.school.service;
import com.example.school.entity.Student;
import com.example.school.enums.Renum;
import com.example.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class StudentService {


    private final StudentRepository studentRepository;

    public String deleteStudent(int id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return "Student deleted";
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }


    public Student updateStudent(Integer id,
                                 Student student) {
        Student current = studentRepository.findById(id).get();
        current.setName(student.getName());
        studentRepository.save(current);
        return current;
    }
    public ResponseEntity<Map<Renum, Object>> getStudentById(int id) {
        Map<Renum, Object> hm = new LinkedHashMap<>();

        try {
            Optional<Student> studentOpt = studentRepository.findById(id);

            if (studentOpt.isPresent()) {
                hm.put(Renum.RESULT, studentOpt.get());
                hm.put(Renum.MESSAGE, "Student found");
                hm.put(Renum.STATUS, true);
                return ResponseEntity.ok(hm);
            } else {
                hm.put(Renum.MESSAGE, "Student not found");
                hm.put(Renum.STATUS, false);
                return ResponseEntity.status(404).body(hm);
            }
        } catch (Exception e) {
            hm.put(Renum.MESSAGE, e.getMessage());
            hm.put(Renum.STATUS, false);
            return ResponseEntity.status(500).body(hm);
        }
    }

}