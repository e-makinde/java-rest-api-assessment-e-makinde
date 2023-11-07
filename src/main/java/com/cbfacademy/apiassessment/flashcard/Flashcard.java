package com.cbfacademy.apiassessment.Flashcard;

import java.util.UUID;

public class Flashcard {

    UUID id;
    String flashcardQuestion;
    String flashcardAnswer;
    String difficulty;
    String topic;

    public enum difficulty {
        EASY, NORMAL, HARD
    }


    public Flashcard(String flashcardQuestion, String flashcardAnswer, String difficulty, String topic) {
        this.id = UUID.randomUUID();
        this.flashcardQuestion = flashcardQuestion;
        this.flashcardAnswer = flashcardAnswer;
        this.difficulty = difficulty;
        this.topic = topic;
    }

    //GETTERS
    public UUID getID() {
        return this.id;
    }

    public String getFlashcardQuestion() {
        return this.flashcardQuestion;
    }

    public String getFlashcardAnswer() {
        return this.flashcardAnswer;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public String getTopic() {
        return this.topic;
    }

    //SETTERS
    public void setFlashcardQuestion(String flashcardQuestion) {
        this.flashcardQuestion = flashcardQuestion;
    }

    public void setFlashcardAnswer(String flashcardAnswer) {
        this.flashcardAnswer = flashcardAnswer;
    }
    
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }    

    public void setTopic(String topic) {
        this.topic = topic;
    }

// difficultly enum
//create separte file for enum
}
