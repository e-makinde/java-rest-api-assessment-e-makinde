package com.cbfacademy.apiassessment.model;

import java.util.UUID;

public class Flashcard {

    private UUID id;
    private String flashcardQuestion;
    private String flashcardAnswer;
    private difficulty difficultyType;
    private String topic;

    public enum difficulty {
        EASY, NORMAL, HARD
    }


    public Flashcard(String flashcardQuestion, String flashcardAnswer, difficulty difficultyType, String topic) {
        this.id = UUID.randomUUID();
        this.flashcardQuestion = flashcardQuestion;
        this.flashcardAnswer = flashcardAnswer;
        this.difficultyType = difficultyType;
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

    public difficulty getDifficulty() {
        return this.difficultyType;
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
    
    public void setDifficulty(difficulty difficultyType) {
        this.difficultyType = difficultyType;
    }    

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
