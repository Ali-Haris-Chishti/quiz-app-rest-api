package dev.haris.quizapp.repository;

import dev.haris.quizapp.model.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepo extends CrudRepository<Quiz, Integer> {

}
