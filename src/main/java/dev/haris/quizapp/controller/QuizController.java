package dev.haris.quizapp.controller;

import dev.haris.quizapp.model.Quiz;
import dev.haris.quizapp.model.Subject;
import dev.haris.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quiz")
public class QuizController {

    QuizService quizService;
    @Autowired
    void setQuizRepo(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("/create/questions")
    ResponseEntity<String> createQuiz(@RequestBody List<Integer> questions){
        return quizService.createQuiz(questions);
    }

    @PostMapping("/create")
    ResponseEntity<String> createParticularSubjectQuiz(
            @RequestParam(required = false) Subject subject,
            @RequestParam(required = false) Integer size
            ){
        return quizService.createSubjectQuiz(subject, size);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<Quiz> getQuizById(@PathVariable int id){
        return quizService.getQuizById(id);
    }

}
