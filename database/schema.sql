DROP DATABASE IF EXISTS quizdb;
CREATE DATABASE IF NOT EXISTS quizdb;
USE quizdb;

CREATE TABLE question(
	`question_id` INT AUTO_INCREMENT,
    `statement` VARCHAR(250),
    `subject` VARCHAR(30),
    `option_one` VARCHAR(30),
    `option_two` VARCHAR(30),
    `option_three` VARCHAR(30),
    `option_four` VARCHAR(30),
    `answer` INT,
    
    PRIMARY KEY (question_id)
);

CREATE TABLE quiz(
	`quiz_id` INT AUTO_INCREMENT,
    `no_of_questions` INT,
    `quiz_date` DATE,
    
    PRIMARY KEY (quiz_id)
);

CREATE TABLE student(
	`student_id` INT AUTO_INCREMENT,
    `first_name` VARCHAR(30),
    `last_name` VARCHAR(30),
    `email` VARCHAR(40),
    `password` VARCHAR(30),
    
    PRIMARY KEY (student_id)
);

CREATE TABLE marks(
	`mark_id` INT AUTO_INCREMENT,
    `quiz_id` INT,
    `student_id` INT,
    `obtained_marks` INT,
    
    PRIMARY KEY (mark_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz (`quiz_id`),
    FOREIGN KEY (student_id) REFERENCES student (`student_id`)
);

CREATE TABLE quiz_questions_relation(
    `quiz_id` INT,
    `question_id` INT,
    
    PRIMARY KEY (quiz_id, question_id),
    FOREIGN KEY (quiz_id) REFERENCES quiz (`quiz_id`),
    FOREIGN KEY (question_id) REFERENCES question (`question_id`)    
);