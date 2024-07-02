package dev.haris.quizapp.repository;

import dev.haris.quizapp.model.Question;
import dev.haris.quizapp.model.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends CrudRepository<Question, Integer> {
    List<Question> findAllBySubject(Subject subject);
}
