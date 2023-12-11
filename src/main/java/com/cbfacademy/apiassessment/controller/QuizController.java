package com.cbfacademy.apiassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.model.Flashcard;
import com.cbfacademy.apiassessment.model.Quiz;
import com.cbfacademy.apiassessment.service.FlashcardServiceImp;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private FlashcardServiceImp flashcardServiceImp;
    @Autowired
    private Quiz quiz;

    @GetMapping("/start")
    public String startQuiz() {
        List<Flashcard> allFlashcards = flashcardServiceImp.getAllFlashcards();
        quiz = new Quiz(allFlashcards);
        quiz.startQuiz();

        return "Quiz started. Get your first question by sending a GET request to /quiz/question.";
    }


    @GetMapping("/question")
    public String getQuestion() {
        if (quiz == null || !quiz.hasNextQuestion()) {
            return "Quiz not started or no more questions. Start a new quiz by sending a GET request to /quiz/start.";
        }

        Flashcard currentQuestion = quiz.getNextQuestion();
        return "Question: " + currentQuestion.getFlashcardQuestion() + "\r\n" + "Check your answer by sending a GET request to quiz/reveal-answer.";
    }

    @PostMapping("/answer")
    public String submitAnswer(@RequestParam boolean isCorrect) {
        if (quiz == null || !quiz.hasCurrentQuestion()) {
            return "Quiz not started or no question available.";
        }

        quiz.userAnswer(isCorrect);
        return "Answer submitted. Send a GET request to /quiz/question for the next question.";
    }


    @GetMapping("/reveal-answer")
    public String revealAnswer() {
        if (quiz == null || !quiz.hasCurrentQuestion()) {
            return "Quiz not started or no question available.";
        }
    
        Flashcard currentQuestion = quiz.getCurrentQuestion();
        return "Answer: " + currentQuestion.getFlashcardAnswer() + "\n" + "Get your next question by sending a GET request to quiz/question.";
    }
}