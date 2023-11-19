package com.cbfacademy.apiassessment.service;


import com.cbfacademy.apiassessment.algorithm.FlashcardLinearSearchAlgorithm;
import com.cbfacademy.apiassessment.exception.DifficultyNotAvailableException;
import com.cbfacademy.apiassessment.model.Flashcard;
import com.cbfacademy.apiassessment.repository.flashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class flashcardServiceImp implements flashcardService {

    @Autowired
    flashcardRepository flashcardRepository;
    @Autowired
    FlashcardLinearSearchAlgorithm flashcardLinearSearchAlgorithm;


    @Override
    public ArrayList<Flashcard> getAllFlashcards(){
        return flashcardRepository.readJSONFile();
    }


    public Flashcard getFlashcard(UUID id){
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        return flashcardLinearSearchAlgorithm.linearSearch(flashcards, id);
    }


    public String getQuestion(UUID id) {
        return getFlashcard(id).getFlashcardQuestion();
    }


    public String getAnswer(UUID id) {
        return getFlashcard(id).getFlashcardAnswer();
    }

    public ArrayList<Flashcard> getFlashcardsByTopic(String topic) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        return flashcardLinearSearchAlgorithm.linearSearch(flashcards, topic);
    }

    public ArrayList<Flashcard> getFlashcardsByDifficulty(String difficulty) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();

        Flashcard.Difficulty flashcardEnum;

        if (difficulty.equalsIgnoreCase("easy")) {
            flashcardEnum = Flashcard.Difficulty.EASY;
        } else if (difficulty.equalsIgnoreCase("normal")) {
            flashcardEnum = Flashcard.Difficulty.NORMAL;
        } else if (difficulty.equalsIgnoreCase("hard")) {
            flashcardEnum = Flashcard.Difficulty.HARD;
        } else throw new DifficultyNotAvailableException("Wrong argument input.");

        return flashcardLinearSearchAlgorithm.linearSearch(flashcards, flashcardEnum);
    }


    @Override
    public void addFlashcard(Flashcard flashcard) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        flashcards.add(flashcard);
        flashcardRepository.writeJSONFile(flashcards);
    }


    @Override
    public void removeFlashcard(UUID id) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        flashcards.removeIf(flashcard -> flashcard.getID().equals(id));
        flashcardRepository.writeJSONFile(flashcards);
    }


    @Override
    public void updateFlashcard(Flashcard clientUpdatedFlashcard){
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        Flashcard foundFlashcard = flashcardLinearSearchAlgorithm.linearSearch(flashcards,clientUpdatedFlashcard.getID());

        foundFlashcard.setFlashcardQuestion(clientUpdatedFlashcard.getFlashcardQuestion());
        foundFlashcard.setFlashcardAnswer(clientUpdatedFlashcard.getFlashcardAnswer());
        foundFlashcard.setDifficulty(clientUpdatedFlashcard.getDifficulty());
        foundFlashcard.setFlashcardTopic(clientUpdatedFlashcard.getFlashcardTopic());

        flashcardRepository.writeJSONFile(flashcards);
    }
            
}
