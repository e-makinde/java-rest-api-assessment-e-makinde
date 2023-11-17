package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.model.Flashcard;

import java.util.List;
import java.util.UUID;

public interface flashcardService {
    default List<Flashcard> getAllFlashcards() {
        return null;
    }

    default void addFlashcard(Flashcard flashcard){
    }

    default void removeFlashcard(UUID id) {
    }

}
