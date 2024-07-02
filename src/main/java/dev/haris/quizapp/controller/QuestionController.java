package dev.haris.quizapp.controller;

import dev.haris.quizapp.model.Question;
import dev.haris.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question")
public class QuestionController {

    QuestionService questionService;
    @Autowired
    void setQuestionService(QuestionService questionService){
        this.questionService = questionService;
    }


    @PostMapping("/add/all")
    ResponseEntity<String> addQuestions(@RequestBody List<Question> questions){
        return questionService.addQuestions(questions);
    }

    @PostMapping("/add")
    ResponseEntity<String> addQuestion(@RequestBody Question question, Errors errors){
        return questionService.addQuestion(question, errors);
    }
}
