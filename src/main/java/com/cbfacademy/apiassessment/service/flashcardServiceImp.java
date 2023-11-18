package com.cbfacademy.apiassessment.service;

import com.cbfacademy.apiassessment.exception.FlashcardNotFoundException;
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

    @Override
    public ArrayList<Flashcard> getAllFlashcards(){
        return flashcardRepository.readJSONFile();
    }

    public Flashcard getFlashcard(UUID id){
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        Flashcard foundFlashcard = null;
            for (Flashcard flashcard : flashcards) {
                if (flashcard.getID().equals(id)) {
                    foundFlashcard = flashcard;
                    return  foundFlashcard;
                } else {
                    throw new FlashcardNotFoundException("Flashcard not found.");
                }
            }
        return foundFlashcard;
    }

    public String getQuestion(UUID id) {
        return getFlashcard(id).getFlashcardQuestion();
    }

    public String getAnswer(UUID id) {
        return getFlashcard(id).getFlashcardAnswer();
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
        // Find flashcard in list, using ID, and remove specified flashcard
        flashcards.removeIf(flashcard -> flashcard.getID().equals(id));

        //Overwrite json with updated flashcard list
        flashcardRepository.writeJSONFile(flashcards);

    }

    @Override
    public void updateFlashcard(Flashcard clientUpdatedFlashcard){
        ArrayList<Flashcard> flashcards = getAllFlashcards();

        boolean flashcardFound = false;

        // Find flashcards in list
        for (Flashcard flashcard:flashcards) {
            //Find flashcard obj and update as per the parameter stated
            if (flashcard.getID().equals(clientUpdatedFlashcard.getID())) {
                flashcard.setFlashcardQuestion(clientUpdatedFlashcard.getFlashcardQuestion());
                flashcard.setFlashcardAnswer(clientUpdatedFlashcard.getFlashcardAnswer());
                flashcard.setDifficulty(clientUpdatedFlashcard.getDifficulty());
                flashcard.setFlashcardTopic(clientUpdatedFlashcard.getFlashcardTopic());
                flashcardFound = true;
            }
        }

        if (!flashcardFound) throw new FlashcardNotFoundException("Not matching flashcard with the id.");

        //Overwrite json with updated flashcard list
        flashcardRepository.writeJSONFile(flashcards);
    }
            
}
