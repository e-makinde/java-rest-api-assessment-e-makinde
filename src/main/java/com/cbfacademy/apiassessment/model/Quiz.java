package com.cbfacademy.apiassessment.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Quiz {
    private ArrayList<Flashcard> flashcards;
    private Iterator<Flashcard> iterator;
    private Flashcard currentQuestion;
    private ArrayList<Flashcard> incorrectFlashcards;

    public Quiz(List<Flashcard> flashcardsList) {
        this.flashcards = new ArrayList<>(flashcardsList);
        this.iterator = flashcards.iterator();
        this.currentQuestion = null;
        this.incorrectFlashcards = new ArrayList<>();

        // Shuffle the order of the flashcards?
    }

    public void startQuiz() {
        for (Flashcard flashcard : flashcards) {
            System.out.println("Question: " + flashcard.getFlashcardQuestion());
            System.out.println("Answer: " + flashcard.getFlashcardAnswer());
        }
    }

    public boolean hasNextQuestion() {
        return iterator.hasNext();
    }

    public Flashcard getNextQuestion() {
        if (iterator.hasNext()) {
            currentQuestion = iterator.next();
            return currentQuestion;
        } else {
            return null; // No more questions
        }
    }

    public boolean hasCurrentQuestion() {
        return currentQuestion != null;
    }

    public Flashcard getCurrentQuestion() {
        return currentQuestion;
    }

    public String userAnswer(boolean isCorrect) {
        if (hasCurrentQuestion()) {
            currentQuestion.setLatestUserResponse(isCorrect);

            if (isCorrect) {
                return "Congratulations on getting the answer correct!";
            } else {
                return "Better luck next time!";
            }
        } else {
            return "No current question";
        }
    }

    public void generateQuizUsingIncorrectAnswers() {
        for (Flashcard flashcard : flashcards) {
            if (flashcard.getLatestUserResponse().equals(false)) {
                incorrectFlashcards.add(flashcard);
            }
        }
    }
}