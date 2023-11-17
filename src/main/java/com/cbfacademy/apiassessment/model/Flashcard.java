package com.cbfacademy.apiassessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Flashcard {

    @JsonProperty("id")
    private final UUID flashcardID;
    @JsonProperty("question")
    private String flashcardQuestion;
    @JsonProperty("answer")
    private String flashcardAnswer;
    @JsonProperty("difficulty")
    private difficulty flashcardDifficultyType;
    @JsonProperty("topic")
    private String flashcardTopic;

    public enum difficulty {
        EASY, NORMAL, HARD
    }


    public Flashcard(String flashcardQuestion, String flashcardAnswer, difficulty flashcardDifficultyType, String flashcardTopic) {
        this.flashcardID = UUID.randomUUID();
        this.flashcardQuestion = flashcardQuestion;
        this.flashcardAnswer = flashcardAnswer;
        this.flashcardDifficultyType = flashcardDifficultyType;
        this.flashcardTopic = flashcardTopic;
    }

    //GETTERS
    public UUID getID() {
        return this.flashcardID;
    }

    public String getFlashcardQuestion() {
        return this.flashcardQuestion;
    }

    public String getFlashcardAnswer() {
        return this.flashcardAnswer;
    }

    public difficulty getDifficulty() {
        return this.flashcardDifficultyType;
    }

    public String getFlashcardTopic() {
        return this.flashcardTopic;
    }

    //SETTERS
    public void setFlashcardQuestion(String flashcardQuestion) {
        this.flashcardQuestion = flashcardQuestion;
    }

    public void setFlashcardAnswer(String flashcardAnswer) {
        this.flashcardAnswer = flashcardAnswer;
    }
    
    public void setDifficulty(difficulty difficultyType) {
        this.flashcardDifficultyType = difficultyType;
    }    

    public void setFlashcardTopic(String flashcardTopic) {
        this.flashcardTopic = flashcardTopic;
    }

}
