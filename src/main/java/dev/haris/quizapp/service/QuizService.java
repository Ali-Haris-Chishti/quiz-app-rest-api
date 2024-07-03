package dev.haris.quizapp.service;

import dev.haris.quizapp.model.Question;
import dev.haris.quizapp.model.Quiz;
import dev.haris.quizapp.model.Subject;
import dev.haris.quizapp.repository.QuestionRepo;
import dev.haris.quizapp.repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class QuizService {

    QuizRepo quizRepo;
    @Autowired
    void setQuizRepo(QuizRepo quizRepo){
        this.quizRepo = quizRepo;
    }

    QuestionRepo questionRepo;
    @Autowired
    void setQuestionRepo(QuestionRepo questionRepo){
        this.questionRepo = questionRepo;
    }

    public ResponseEntity<Quiz> createQuiz(List<Integer> questions) {
        Vector<Integer>[] questionDivision = new Vector[2];
        questionDivision[0] = new Vector<>();
        questionDivision[1] = new Vector<>();
        Quiz quiz;
        for (int q: questions){
            Optional<Question> question = questionRepo.findById(q);
            if (question.isEmpty())
                questionDivision[1].add(q);
            else
                questionDivision[0].add(q);
        }
        if (!questionDivision[0].isEmpty()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();

            Set<Question> quizQuestions = new HashSet<>();
            StringBuilder status = new StringBuilder("Quiz Created with questions: ");
            for (int a: questionDivision[0]){
                status.append(a).append("\t");
                quizQuestions.add(questionRepo.findById(a).get());
            }
            status.append("\tQuestions not Available: ");
            for (int n: questionDivision[1])
                status.append(n).append("\t");

            quiz = new Quiz(questionDivision[0].size(), date.format(formatter), quizQuestions);
            quizRepo.save(quiz);

            return new ResponseEntity<>(quiz, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new Quiz(), HttpStatus.EXPECTATION_FAILED);
    }

    public ResponseEntity<Quiz> createSubjectQuiz(Subject subject, Integer size) {
        List<Question> questions;
        if (subject == null){
            questions = (List<Question>) questionRepo.findAll();
        }
        else {
            questions = questionRepo.findAllBySubject(subject);
        }
        if (size == null)
            size = 20;
        List<Integer> indexes = generateRandomDistinctNumbers(questions.size(), size);
        Set<Question> quizQuestions = new HashSet<>();
        for (int index: indexes)
            quizQuestions.add(questions.get(index));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        Quiz quiz = new Quiz(quizQuestions.size(), date.format(formatter), quizQuestions);
        quizRepo.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.CREATED);
    }

    public static List<Integer> generateRandomDistinctNumbers(int max, int count) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> usedNumbers = new HashSet<>();

        if (max < count) {
            for (int i = 0; i < max; i++) {
                result.add(i);
            }
            return result;
        }

        Random random = new Random();
        while (result.size() < count) {
            int randomNumber = random.nextInt(max);
            if (!usedNumbers.contains(randomNumber)) {
                result.add(randomNumber);
                usedNumbers.add(randomNumber);
            }
        }

        return result;
    }

    public ResponseEntity<Quiz> getQuizById(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

        return quiz.map(value -> new ResponseEntity<>(value, HttpStatus.FOUND)).orElseGet(() -> new ResponseEntity<>(new Quiz(), HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Quiz> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

        return new ResponseEntity<>(quiz.get(), HttpStatus.FOUND);
    }

    public ResponseEntity<List<Quiz>> gatAllQuizzes() {
        List<Quiz> quizzes = quizRepo.findAll();
        return new ResponseEntity<>(quizzes, HttpStatus.FOUND);
    }
}
