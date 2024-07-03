package dev.haris.quizapp.repository;

import dev.haris.quizapp.model.Question;
import dev.haris.quizapp.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {
    List<Question> findAllBySubject(Subject subject);
}
