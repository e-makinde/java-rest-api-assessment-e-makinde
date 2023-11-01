package com.cbfacademy.apiassessment.flashcard;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cbfacademy.apiassessment.JSONFileHandler;

@RestController
@RequestMapping("/flashcard")
public class FlashcardController {
    //initiate list for flashcard objects to be held in.
    private final List<Flashcard> flashcards = new ArrayList<>();

    //Return all questions and answers the have been saved
	@GetMapping("/")
    public List<Flashcard> getAllFlashcardQandA() {
        return flashcards;
    }

    //Return an individual question using the ID
    @GetMapping("/question{id}")
    public Flashcard getQuestionByID(@PathVariable("id") String id) {
        return new Flashcard("Where are you?", "London", "Easy", "Location");
    
    }

    //Return an individual answer using the ID
    @GetMapping("/answer{id}")
    public String getAnswerByID(@PathVariable("id") String id) {
        // Search Flashcards. Return answer with corresponding ID
        return 
        
    }

    //Get all questions with a certain difficulty
    @GetMapping("/questions{difficulty}")
    public List getQuestionsByDifficulty(@PathVariable("difficulty") String difficulty) {
        
    }

    //Get all questions within a certain topic
    @GetMapping("/questions{topic}")
    public List getQuestionsByTopic(@PathVariable("topic") String topic) {

    }

    @PostMapping(path="/", produces="application/json")
    public void createFlashcard(@RequestBody Flashcard flashcard) {
        flashcards.add(flashcard);
        JSONFileHandler.writeFile(flashcard, "Flashcards.json");
    }


    
}
