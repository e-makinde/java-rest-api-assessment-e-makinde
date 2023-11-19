package com.cbfacademy.apiassessment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.cbfacademy.apiassessment.response.ResponseHandler;
import com.cbfacademy.apiassessment.service.flashcardServiceImp;
import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    @Autowired
    private flashcardServiceImp flashcardServiceImp;


    //Return all questions and answers that have been saved
	@GetMapping("/all")
    public ResponseEntity<Object> getAllFlashcards() {
        return ResponseHandler.responseBuilder("Here are all of the stored flashcards", HttpStatus.OK, flashcardServiceImp.getAllFlashcards());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFlashcardByID(@PathVariable("id") UUID id) {
        return ResponseHandler.responseBuilder("Here is the requested flashcard", HttpStatus.OK, flashcardServiceImp.getFlashcard(id));
    }

    //Return an individual question using the ID
    @GetMapping("/question/{id}")
    public ResponseEntity<Object> getQuestionByID(@PathVariable("id") UUID id) { return ResponseHandler.responseBuilder("Here is the requested question", HttpStatus.OK, flashcardServiceImp.getQuestion(id));}

    //Return an individual answer using the ID
    @GetMapping("/answer/{id}")
    public ResponseEntity<Object> getAnswerByID(@PathVariable("id") UUID id) { return ResponseHandler.responseBuilder("Here is the requested answer", HttpStatus.OK, flashcardServiceImp.getAnswer(id));}

    //Get all questions with a certain difficulty
    @GetMapping("/questions/difficulty/{difficulty}")
    public ResponseEntity<Object> getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        ArrayList<Flashcard> flashcards = flashcardServiceImp.getAllFlashcards();
        ArrayList<Flashcard> sameDifficultyFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getDifficulty().toString().equals(difficulty)) {
                sameDifficultyFlashcards.add(flashcard);
            }
        }
        return ResponseHandler.responseBuilder("Here are flashcards with the same difficulty.", HttpStatus.OK, sameDifficultyFlashcards);
    }

    //Get all questions within a certain topic
    @GetMapping("/questions/topic/{topic}")
    public ResponseEntity<Object> getQuestionsByTopic(@PathVariable("topic") String topic) {
        ArrayList<Flashcard> flashcards = flashcardServiceImp.getAllFlashcards();
        ArrayList<Flashcard> sameTopicFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getFlashcardTopic().equals(topic)) {
                sameTopicFlashcards.add(flashcard);
            }
        }
        return ResponseHandler.responseBuilder("Here are flashcards with the same topic.", HttpStatus.OK, sameTopicFlashcards);
    }


    @PostMapping(path="/new", produces="application/json")
    public String createFlashcard(@Valid @RequestBody Flashcard flashcard) {
        flashcardServiceImp.addFlashcard(flashcard);
        return "Flashcard created successfully.";
    }


    @PutMapping(path = "/update", produces = "application/json")
    public String updateFlashcard(@Valid @RequestBody Flashcard flashcard) {
        flashcardServiceImp.updateFlashcard(flashcard);
        return "Flashcard updated successfully";
    }


    @DeleteMapping("/delete")
    public String deleteFlashcardByID(UUID id) {
        flashcardServiceImp.removeFlashcard(id);
        return "Flashcard deleted succesfully";
    }

}
