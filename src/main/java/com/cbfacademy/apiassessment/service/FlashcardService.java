package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.Flashcard;

import java.util.List;
import java.util.UUID;

public interface FlashcardService {
    default List<Flashcard> getAllFlashcards() { return null; }

    default Flashcard getFlashcard(UUID id) { return null; }

    default void addFlashcard(Flashcard flashcard) {}

    default void removeFlashcard(UUID id) {}

    default void updateFlashcard(Flashcard updatedFlashcard) {}

}
