package dev.haris.quizapp.service;

import dev.haris.quizapp.model.Question;
import dev.haris.quizapp.model.Quiz;
import dev.haris.quizapp.repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class QuestionService {

    QuestionRepo questionRepo;
    @Autowired
    void setQuestionRepo(QuestionRepo questionRepo){
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<String> addQuestions(List<Question> questions){
        if (questions == null)
            return new ResponseEntity<>("You must enter the questions to add", HttpStatus.CONFLICT);
        if (questions.isEmpty())
            return new ResponseEntity<>("No Questions to add", HttpStatus.NO_CONTENT);
        questionRepo.saveAll(questions);
        return new ResponseEntity<>("Questions added successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<String> addQuestion(Question question, Errors errors) {
        if (errors.hasErrors()){
            String error = errors.toString();
            return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
        }
        questionRepo.save(question);
        return new ResponseEntity<>("Question added Successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<Question> getQuestionById(int id) {
        Optional<Question> question = questionRepo.findById(id);

        return question.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(new Question(), HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Set<Quiz>> getQuizzesWithThisQuestion(int id) {
        Optional<Question> question = questionRepo.findById(id);

        return new ResponseEntity<>(question.get().getQuizzes(), HttpStatus.FOUND);
    }
}
