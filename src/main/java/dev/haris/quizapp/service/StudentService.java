package dev.haris.quizapp.service;

import dev.haris.quizapp.controller.StudentController;
import dev.haris.quizapp.model.Student;
import dev.haris.quizapp.repository.StudentRepo;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    StudentRepo studentRepo;
    @Autowired
    void setStudentRepo(StudentRepo studentRepo){
        this.studentRepo = studentRepo;
    }


    Validator validator;
    @Autowired
    void setValidator(Validator validator){
        this.validator = validator;
    }


    public ResponseEntity<StudentController.StudentStatus> addStudent(Student student) {
        Set<ConstraintViolation<Student>> violations =  validator.validate(student);
        if (violations.isEmpty()) {
            StudentController.StudentStatus studentStatus =
                    new StudentController.StudentStatus(
                        studentRepo.save(student),
                "Student added successfully"
                    );
            return new ResponseEntity<>(studentStatus, HttpStatus.CREATED);
        }
        else{
            StringBuilder v = new StringBuilder("Errors adding student: ");
            for (ConstraintViolation<Student> violation: violations){
                v.append(violation.getMessage()).append(", ");
            }
            return new ResponseEntity<>(new StudentController.StudentStatus(new Student(), v.toString()), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<StudentController.StudentStatus> getStudent(int id) {
        Optional<Student> student = studentRepo.findById(id);

        if (student.isPresent()){
            String message = "Student Fetched Successfully";
            return new ResponseEntity<>(new StudentController.StudentStatus(student.get(), message), HttpStatus.FOUND);
        }
        else {
            String message = "Student with id " + id + " does not exists";
            return new ResponseEntity<>(new StudentController.StudentStatus(new Student(), message), HttpStatus.FOUND);
        }
    }
}
