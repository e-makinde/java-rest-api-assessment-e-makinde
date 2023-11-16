package com.cbfacademy.apiassessment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cbfacademy.apiassessment.Flashcard.JSONFileHandler;
import com.cbfacademy.apiassessment.model.Flashcard;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {
    List<Flashcard> flashcards = new ArrayList<>();

    //Return all questions and answers that have been saved
	@GetMapping("/all")
    public String getAllFlashcards(Model model) {
        flashcards = JSONFileHandler.readJSONFile("src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");
        model.addAttribute("flashcards",flashcards);
        return "AllFlashcards";
    }

    //Return an individual question using the ID
    @GetMapping("/question/{id}")
    public String getQuestionByID(@PathVariable("id") String id, Model model) {

        flashcards = JSONFileHandler.readJSONFile("src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                model.addAttribute("question", flashcard.getFlashcardQuestion());
                return flashcard.getFlashcardQuestion();
            }
        }
        return "No question found";
    
    }

    //Return an individual answer using the ID
    @GetMapping("/answer/{id}")
    public String getAnswerByID(@PathVariable("id") String id, Model model) {
        flashcards = JSONFileHandler.readJSONFile("src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                model.addAttribute(flashcard.getFlashcardAnswer());
                return flashcard.getFlashcardAnswer();
            }
        }
        return "No Answer found";

    }

    //Get all questions with a certain difficulty
    @GetMapping("/questions/difficulty/{difficulty}")
    public List<Flashcard> getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        flashcards = JSONFileHandler.readJSONFile("src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");
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
        flashcards = JSONFileHandler.readJSONFile("src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");
        List<Flashcard> sameTopicFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getTopic().equals(topic)) {
                sameTopicFlashcards.add(flashcard);
            }
        }
        return sameTopicFlashcards;
    }

    @PostMapping(path="/new", produces="application/json")
    public void createFlashcard(Flashcard flashcard) {

        JSONFileHandler.addFlashcard(flashcard, "src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlashcard(@PathVariable("id") @ModelAttribute Flashcard flashcard) {
        UUID flashcardToDeleteID = flashcard.getID();
        JSONFileHandler.removeFlashcard(flashcardToDeleteID, "src/main/java/com/cbfacademy/apiassessment/Flashcard/userFlashcards/Flashcards.json");
    }



    
}
