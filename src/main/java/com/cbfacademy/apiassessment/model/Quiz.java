package com.cbfacademy.apiassessment.model;

import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private ArrayList<Flashcard> flashcards;

    public Quiz(List<Flashcard> flashcardsList) {
        this.flashcards = new ArrayList<>(flashcardsList);

        // Shuffle the order of the flashcards?
    }

    public void startQuiz() {
        for (Flashcard flashcard : flashcards) {
            System.out.println("Question: " + flashcard.getFlashcardQuestion());
            System.out.println("Answer: " + flashcard.getFlashcardAnswer());
        }
    }
}