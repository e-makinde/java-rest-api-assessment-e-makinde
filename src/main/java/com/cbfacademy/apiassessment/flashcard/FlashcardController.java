package com.cbfacademy.apiassessment.Flashcard;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//create json file put in separate folder

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
    public Flashcard getQuestionByID(@PathVariable("id") String id) {
        return new Flashcard("Where are you?", "London", "Easy", "Location");
    
    }

    //Return an individual answer using the ID
    @GetMapping("/answer{id}")
    public String getAnswerByID(@PathVariable("id") String id) {
        //search all flashcards and return the answer of the specific question
        List<Flashcard> flashcards = new ArrayList<Flashcard>();
        //ListIterator<Flashcard> flashcardListIterator = new ListIterator<Flashcard>();

        
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
        JSONFileHandler.addFlashcard(flashcard, "Flashcards.json");
    }


    
}
