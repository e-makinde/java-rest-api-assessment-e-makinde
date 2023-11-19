package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.model.Flashcard;

import java.util.UUID;

public class FakeFlashcard extends Flashcard {

    private final UUID fakeUUID;

    public FakeFlashcard(UUID fakeUUID, String flashcardQuestion, String flashcardAnswer, difficulty flashcardDifficultyType, String flashcardTopic) {
        super(flashcardQuestion, flashcardAnswer, flashcardDifficultyType, flashcardTopic);
        this.fakeUUID = fakeUUID;
    }

    @Override
    public UUID getID() {
        return fakeUUID;
    }
}
