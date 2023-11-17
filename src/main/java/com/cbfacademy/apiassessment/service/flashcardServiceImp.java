package com.cbfacademy.apiassessment.service;

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
    public ArrayList<Flashcard> getAllFlashcards() {
        return flashcardRepository.readJSONFile();
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


    public void updateFlashcard(Flashcard updatedFlashcard) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        // Find flashcards in list
        for (Flashcard flashcard:flashcards) {
            //Find flashcard obj and update as per the parameter stated
            if (flashcard.getID().equals(updatedFlashcard.getID())) {
                flashcard.setFlashcardQuestion(updatedFlashcard.getFlashcardQuestion());
                flashcard.setFlashcardAnswer(updatedFlashcard.getFlashcardAnswer());
                flashcard.setDifficulty(updatedFlashcard.getDifficulty());
                flashcard.setFlashcardTopic(updatedFlashcard.getFlashcardTopic());
            }
        }
        //Overwrite json with updated flashcard list
        flashcardRepository.writeJSONFile(flashcards);
    }

    // add exception handling flashcard not found
    public Flashcard getFlashcardByID(String id) {
        ArrayList<Flashcard> flashcards = getAllFlashcards();
        Flashcard foundFlashcard = null;

        // Find flashcards in list
        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().equals(UUID.fromString(id))) {
                foundFlashcard = flashcard;
                return foundFlashcard;
            }
        }
        return foundFlashcard;
    }
            
}
