package com.cbfacademy.apiassessment.Flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {

    //Return all questions and answers that have been saved
	@GetMapping("/")
    public List<Flashcard> getAllFlashcardQandA() {
        return JSONFileHandler.readJSONFile("Flashcards.json");
    }

    //Return an individual question using the ID
    @GetMapping("/question{id}")
    public String getQuestionByID(@PathVariable("id") String id) {
        List<Flashcard> flashcards = getAllFlashcardQandA();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                return flashcard.getFlashcardQuestion();
            }
        }
        return "No question found";
    
    }

    //Return an individual answer using the ID
    @GetMapping("/answer{id}")
    public String getAnswerByID(@PathVariable("id") String id) {
        List<Flashcard> flashcards = getAllFlashcardQandA();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getID().toString().equals(id)) {
                return flashcard.getFlashcardAnswer();
            }
        }
        return "No Answer found";

    }

    //Get all questions with a certain difficulty
    @GetMapping("/questions{difficulty}")
    public List getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        List<Flashcard> flashcards = getAllFlashcardQandA();
        List<Flashcard> sameDifficultyFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getDifficulty().equals(difficulty)) {
                sameDifficultyFlashcards.add(flashcard);
            }
        }
        return sameDifficultyFlashcards;
    }

    //Get all questions within a certain topic
    @GetMapping("/questions{topic}")
    public List getQuestionsByTopic(@PathVariable("topic") String topic) {
        List<Flashcard> flashcards = getAllFlashcardQandA();
        List<Flashcard> sameTopicFlashcards = new ArrayList<>();

        for (Flashcard flashcard:flashcards) {
            if (flashcard.getDifficulty().equals(topic)) {
                sameTopicFlashcards.add(flashcard);
            }
        }
        return sameTopicFlashcards;
    }

    @PostMapping(path="/", produces="application/json")
    public void createFlashcard(@RequestBody Flashcard flashcard) {
        JSONFileHandler.addFlashcard(flashcard, "Flashcards.json");
    }

    @DeleteMapping
    public void deleteFlashcard(@RequestBody Flashcard flashcard) {
        UUID flashcardToDeleteID = flashcard.getID();
        JSONFileHandler.removeFlashcard(flashcardToDeleteID, "Flashcards.json");
    }



    
}
