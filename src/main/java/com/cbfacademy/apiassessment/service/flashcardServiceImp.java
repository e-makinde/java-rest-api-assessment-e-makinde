package com.cbfacademy.apiassessment.service;


import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.cbfacademy.apiassessment.repository.flashcardRepository.writeJSONFile;
import static com.cbfacademy.apiassessment.repository.flashcardRepository.readJSONFile;

@Service
public class flashcardServiceImp implements flashcardService {
    static List<Flashcard> flashcards = new ArrayList<>(getAllFlashcards());


    public static List<Flashcard> getAllFlashcards() {
        return readJSONFile();
    }

    // add exception handling flashcard not found
    public static Flashcard getFlashcardByID(String id) {
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


    public static void addFlashcard(Flashcard flashcard) {
        flashcards.add(flashcard);
        writeJSONFile(flashcards);
    }

    public static void removeFlashcard(UUID id) {
        // Find flashcard in list, using ID, and remove specified flashcard
        flashcards.removeIf(flashcard -> flashcard.getID().equals(id));

        //Overwrite json with updated flashcard list
        writeJSONFile(flashcards);
    }

    public static void updateFlashcard(Flashcard updatedFlashcard) {

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
        writeJSONFile(flashcards);

    }
            
}
