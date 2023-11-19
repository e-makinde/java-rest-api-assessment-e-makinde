package com.cbfacademy.apiassessment.algorithm;

import com.cbfacademy.apiassessment.exception.FlashcardNotFoundException;
import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class FlashcardLinearSearchAlgorithm {

    public Flashcard linearSearch(ArrayList<Flashcard> flashcards, UUID targetId) {
        Flashcard foundFlashcard = null;
        boolean isFlashcardFound = false;

        for (Flashcard flashcard : flashcards) {
            if (flashcard.getID().equals(targetId)) {
                foundFlashcard = flashcard;
                isFlashcardFound = true;
            }
        }

        if (!isFlashcardFound) throw new FlashcardNotFoundException("Flashcard not found with given ID.");

        return foundFlashcard;
    }

    public ArrayList<Flashcard> linearSearch(ArrayList<Flashcard> allFlashcards, Flashcard.Difficulty target) {
        ArrayList<Flashcard> groupedFlashcards = new ArrayList<>();
        boolean areFlashcardsFound = false;

        for (Flashcard flashcard : allFlashcards) {
            if (flashcard.getDifficulty().equals(target)) {
                groupedFlashcards.add(flashcard);
                areFlashcardsFound = true;
            }
        }

        if (!areFlashcardsFound) throw new FlashcardNotFoundException("No flashcards found with the Topic ('%s') ".formatted(target));
        return  groupedFlashcards;
    }

    public ArrayList<Flashcard> linearSearch(ArrayList<Flashcard> allFlashcards, String target) {
        ArrayList<Flashcard> groupedFlashcards = new ArrayList<>();
        boolean areFlashcardsFound = false;

        for (Flashcard flashcard : allFlashcards) {
            if (flashcard.getFlashcardTopic().equals(target)) {
                groupedFlashcards.add(flashcard);
                areFlashcardsFound = true;
            }
        }

        if (!areFlashcardsFound) throw new FlashcardNotFoundException("No flashcards found with the Difficulty ('%s') ".formatted(target));
        return  groupedFlashcards;
    }
}

