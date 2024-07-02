package dev.haris.quizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int questionId;

    @Column
    @NotBlank(message = "Question must have a statement")
    String statement;

    @Column
    @NotNull(message = "Subject can not have null value")
    @Enumerated(EnumType.STRING)
    Subject subject;

    @Column
    @NotBlank(message = "First Option for question is missing")
    String optionOne;

    @Column
    @NotBlank(message = "Second Option for question is missing")
    String optionTwo;

    @Column
    @NotBlank(message = "Third Option for question is missing")
    String optionThree;

    @Column
    @NotBlank(message = "Fourth Option for question is missing")
    String optionFour;

    @Column
    @NotNull(message = "Answer can not be null")
    @Min(value = 1, message = "answer value can not be less than 1")
    @Max(value = 4, message = "answer value can not be more than 4")
    int answer;

    @ManyToMany(mappedBy = "questions", fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    Set<Quiz> quizzes;
}
