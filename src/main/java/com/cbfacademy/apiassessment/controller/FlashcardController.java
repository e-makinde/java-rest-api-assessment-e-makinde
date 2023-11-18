package com.cbfacademy.apiassessment.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.cbfacademy.apiassessment.service.flashcardServiceImp;
import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    @Autowired
    private flashcardServiceImp flashcardServiceImp;


    //Return all questions and answers that have been saved
	@GetMapping("/all")
    public ArrayList<Flashcard> getAllFlashcards() {
        return flashcardServiceImp.getAllFlashcards();
    }

    @GetMapping("/{id}")
    public Flashcard getFlashcardByID(@PathVariable("id") UUID id) {
        return flashcardServiceImp.getFlashcard(id);
    }

    //Return an individual question using the ID
    @GetMapping("/question/{id}")
    public String getQuestionByID(@PathVariable("id") UUID id) { return flashcardServiceImp.getQuestion(id);}

    //Return an individual answer using the ID
    @GetMapping("/answer/{id}")
    public String getAnswerByID(@PathVariable("id") UUID id) {return flashcardServiceImp.getAnswer(id);}

    //Get all questions with a certain difficulty
    @GetMapping("/questions/difficulty/{difficulty}")
    public ArrayList<Flashcard> getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        ArrayList<Flashcard> flashcards = flashcardServiceImp.getAllFlashcards();
        ArrayList<Flashcard> sameDifficultyFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getDifficulty().toString().equals(difficulty)) {
                sameDifficultyFlashcards.add(flashcard);
            }
        }
        return sameDifficultyFlashcards;
    }

    //Get all questions within a certain topic
    @GetMapping("/questions/topic/{topic}")
    public ArrayList<Flashcard> getQuestionsByTopic(@PathVariable("topic") String topic) {
        ArrayList<Flashcard> flashcards = flashcardServiceImp.getAllFlashcards();
        ArrayList<Flashcard> sameTopicFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getFlashcardTopic().equals(topic)) {
                sameTopicFlashcards.add(flashcard);
            }
        }
        return sameTopicFlashcards;
    }


    @PostMapping(path="/new", produces="application/json")
    public void createFlashcard(@RequestBody Flashcard flashcard) throws IOException {
        flashcardServiceImp.addFlashcard(flashcard);
    }


    @PutMapping(path = "/update", produces = "application/json")
    public void updateFlashcard(@RequestBody Flashcard flashcard) throws IOException {
        flashcardServiceImp.updateFlashcard(flashcard);
    }


    @DeleteMapping("/delete")
    public void deleteFlashcardByID(UUID id) throws IOException {
        flashcardServiceImp.removeFlashcard(id);
    }

}
