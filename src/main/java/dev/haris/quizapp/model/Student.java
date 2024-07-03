package dev.haris.quizapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int studentId;

    @Column
    @NotEmpty(message = "First name is missing")
    String firstName;

    @Column
    @NotEmpty(message = "Last name is missing")
    String lastName;

    @Column
    @Email(message = "Not a valid Email")
    @NotEmpty(message = "Email can not be empty")
    String email;

    @Column
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 8, max = 20, message = "Password must be 8-20 characters long")
    String password;
}
