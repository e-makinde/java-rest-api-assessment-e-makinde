package com.cbfacademy.apiassessment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cbfacademy.apiassessment.repository.flashcardRepository;
import com.cbfacademy.apiassessment.service.flashcardServiceImp;
import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {



    String JSONPath = "src/main/java/com/cbfacademy/apiassessment/Flashcard/Flashcards.json";
    List<Flashcard> flashcards = new ArrayList<>(flashcardRepository.readJSONFile());

    public enum flashcardAttribute {
        flashcardQuestion, flashcardAnswer, difficultyType, topic
    }

    //Return all questions and answers that have been saved
	@GetMapping("/all")
    public List<Flashcard> getAllFlashcards() {
        return flashcards;
    }

    //Return an individual question using the ID
    @GetMapping("/question/{id}")
    public String getQuestionByID(@PathVariable("id") String id) {
        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                return flashcard.getFlashcardQuestion();
            }
        }
        return "No question found";
    
    }

    //Return an individual answer using the ID
    @GetMapping("/answer/{id}")
    public String getAnswerByID(@PathVariable("id") String id) {
        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                return flashcard.getFlashcardAnswer();
            }
        }
        return "No Answer found";

    }

    //Get all questions with a certain difficulty
    @GetMapping("/questions/difficulty/{difficulty}")
    public List<Flashcard> getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        List<Flashcard> sameDifficultyFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getDifficulty().equals(difficulty)) {
                sameDifficultyFlashcards.add(flashcard);
            }
        }
        return sameDifficultyFlashcards;
    }

    //Get all questions within a certain topic
    @GetMapping("/questions/topic/{topic}")
    public List<Flashcard> getQuestionsByTopic(@PathVariable("topic") String topic) {
        List<Flashcard> sameTopicFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getTopic().equals(topic)) {
                sameTopicFlashcards.add(flashcard);
            }
        }
        return sameTopicFlashcards;
    }

    @PostMapping(path="/new", produces="application/json")
    public void createFlashcard(@RequestBody Flashcard flashcard) {
        flashcardServiceImp.addFlashcard(flashcard);
    }

    @PutMapping(path = "/update", produces = "application/json")
    public void updateFlashcard(@RequestBody Flashcard flashcard) {
        flashcardServiceImp.updateFlashcard(flashcard);
    }


    @DeleteMapping("/delete/{id}")
    public void deleteFlashcardByID(@PathVariable("id") String id) {

        UUID flashcardToDeleteID = UUID.fromString(id);
        flashcardServiceImp.removeFlashcard(flashcardToDeleteID);

    }

}
