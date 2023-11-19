package com.cbfacademy.apiassessment.model;

import com.cbfacademy.apiassessment.FakeFlashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("The test for the Flashcard class should...")
class FlashcardTest {

    private Flashcard flashcard;

    @BeforeEach
    void setUp() {
        flashcard = new Flashcard("How do you insert COMMENTS in Java code?", "//", Flashcard.Difficulty.EASY, "Java Basics");
    }

    @Test
    @DisplayName("Check that the ID has been set.")
    void testIDIsNotNull() {
        assertNotNull(flashcard.getID()); //Check that ID has been set automatically and the value is not null
    }

    @Test
    @DisplayName("Check that the ID getter works.")
    void testGetID() {
        UUID fakeUUID = UUID.randomUUID();
        Flashcard fakeFlashcard = new FakeFlashcard(fakeUUID, "question", "answer", Flashcard.Difficulty.EASY, "topic");

        assertEquals(fakeUUID, fakeFlashcard.getID());

    }

    @Test
    @DisplayName("Check that the Question getter works.")
    void getFlashcardQuestion() {
        assertEquals("How do you insert COMMENTS in Java code?", flashcard.getFlashcardQuestion());
    }

    @Test
    @DisplayName("Check that the Answer getter works.")
    void getFlashcardAnswer() {
        assertEquals("//", flashcard.getFlashcardAnswer());

    }

    @Test
    @DisplayName("Check that the Difficulty getter works.")
    void getDifficulty() {
        assertEquals(Flashcard.Difficulty.EASY, flashcard.getDifficulty());
    }

    @Test
    @DisplayName("Check that the Topic getter works.")
    void getFlashcardTopic() {
        assertEquals("Java Basics", flashcard.getFlashcardTopic());
    }

    @Test
    @DisplayName("Check that the Question setter works.")
    void setFlashcardQuestion() {
        flashcard.setFlashcardQuestion("Question");

        assertEquals("Question", flashcard.getFlashcardQuestion());
    }

    @Test
    @DisplayName("Check that the Answer setter works.")
    void setFlashcardAnswer() {
        flashcard.setFlashcardAnswer("Answer");

        assertEquals("Answer", flashcard.getFlashcardAnswer());
    }

    @Test
    @DisplayName("Check that the Difficulty setter works.")
    void setDifficulty() {
        flashcard.setDifficulty(Flashcard.Difficulty.NORMAL);

        assertEquals(Flashcard.Difficulty.NORMAL, flashcard.getDifficulty());
    }

    @Test
    @DisplayName("Check that the Topic setter works.")
    void setFlashcardTopic() {
        flashcard.setFlashcardTopic("Topic");

        assertEquals("Topic", flashcard.getFlashcardTopic());
    }
}