package com.cbfacademy.apiassessment.algorithm;

import com.cbfacademy.apiassessment.exception.FlashcardNotFoundException;
import com.cbfacademy.apiassessment.model.Flashcard;

import java.util.ArrayList;
import java.util.UUID;

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

        return foundFlashcard; // Flashcard with the specified ID not found
        }


}
