package dev.haris.quizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int quizId;

//    @Column
//    @Min(value = 1, message = "No of questions must be greater than 0")
//    @Max(value = 100, message = "No of questions must not be greater than 100")
    int noOfQuestions;

//    @Column
//    @NotEmpty(message = "Must enter date when quiz was created")
    String  quizDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "quiz_questions_relation",
            joinColumns = {@JoinColumn(name = "quiz_id", referencedColumnName = "quizId")},
            inverseJoinColumns = {@JoinColumn(name = "question_id", referencedColumnName = "questionId")}
    )
    Set<Question> questions = new HashSet<>();

    public Quiz(int noOfQuestions, String quizDate, Set<Question> questions) {
        this.noOfQuestions = noOfQuestions;
        this.quizDate = quizDate;
        this.questions = questions;
    }
}
