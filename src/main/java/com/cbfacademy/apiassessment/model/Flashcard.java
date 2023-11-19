package com.cbfacademy.apiassessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.validator.constraints.NotBlank;
import org.jetbrains.annotations.NotNull;


import java.util.UUID;

@JsonPropertyOrder({ "id", "question", "answer", "difficulty", "topic" })
public class Flashcard {

    @JsonProperty("id")
    private UUID flashcardID;

    @JsonProperty("question") @NotBlank(message = "Question cannot be blank.")
    private String flashcardQuestion;

    @JsonProperty("answer") @NotBlank(message = "Answer cannot be blank")
    private String flashcardAnswer;

    @JsonProperty("difficulty") @NotNull
    private Flashcard.Difficulty flashcardDifficultyType;

    @JsonProperty("topic") @NotBlank (message = "Topic cannot be blank")
    private String flashcardTopic;


    public enum Difficulty {
        EASY, NORMAL, HARD
    }


    public Flashcard(String flashcardQuestion, String flashcardAnswer, Difficulty flashcardDifficultyType, String flashcardTopic) {
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

    public Difficulty getDifficulty() {
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
    
    public void setDifficulty(Difficulty difficultyType) {
        this.flashcardDifficultyType = difficultyType;
    }    

    public void setFlashcardTopic(String flashcardTopic) {
        this.flashcardTopic = flashcardTopic;
    }

}
