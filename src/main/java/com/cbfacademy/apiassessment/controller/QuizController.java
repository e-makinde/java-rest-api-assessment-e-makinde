package com.cbfacademy.apiassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.model.Flashcard;
import com.cbfacademy.apiassessment.model.Quiz;
import com.cbfacademy.apiassessment.service.FlashcardServiceImp;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private FlashcardServiceImp flashcardServiceImp;

    @GetMapping("/start")
    public void startQuiz() {
        List<Flashcard> allFlashcards = flashcardServiceImp.getAllFlashcards();
        Quiz quiz = new Quiz(allFlashcards);
        quiz.startQuiz();
    }
}