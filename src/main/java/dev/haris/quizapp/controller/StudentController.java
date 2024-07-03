package dev.haris.quizapp.controller;

import dev.haris.quizapp.model.Student;
import dev.haris.quizapp.service.StudentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    StudentService studentService;
    @Autowired
    void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StudentStatus{
        Student student;
        String status;
    }

    @PostMapping("/add")
    ResponseEntity<StudentStatus> addStudent(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("get/{id}")
    ResponseEntity<StudentStatus> getStudent(@PathVariable int id){
        return studentService.getStudent(id);
    }

}
