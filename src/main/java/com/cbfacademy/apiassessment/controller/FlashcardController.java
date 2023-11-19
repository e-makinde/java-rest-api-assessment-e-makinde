package com.cbfacademy.apiassessment.controller;

import java.util.UUID;

import com.cbfacademy.apiassessment.response.ResponseHandler;
import com.cbfacademy.apiassessment.service.FlashcardServiceImp;
import com.cbfacademy.apiassessment.model.Flashcard;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/flashcard")
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact (name = "Esther", email = "esthermakinde@hotmail.com"),
                title = "Flashcard Revision API Assessment",
                description = "This is a Flashcard revision service that allows users to store their desired flashcards and generate lists of flashcards by topic & difficulty."
        ),
        servers = @Server(
                description = "local environment",
                url = "http://localhost:8080")
)
public class FlashcardController {



    @Autowired
    private FlashcardServiceImp flashcardServiceImp;



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Retrieves the Flashcard array.",
            summary = "Gets all stored flashcards."
    )
	@GetMapping("/all")
    public ResponseEntity<Object> getAllFlashcards() {
        return ResponseHandler.responseBuilder("Here are all of the stored flashcards.", HttpStatus.OK, flashcardServiceImp.getAllFlashcards());
    }



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Uses a linear sort algorithm to retrieve the specific Flashcard with the given ID in the array list.",
            summary = "Gets the flashcard with the specified ID."
    )
    @GetMapping("/{id}")
    public ResponseEntity<Object> getFlashcardByID(@PathVariable("id") UUID id) {
        return ResponseHandler.responseBuilder("Here is the requested flashcard with ID: " + id.toString(), HttpStatus.OK, flashcardServiceImp.getFlashcard(id));
    }



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Uses a linear search algorithm to find the specific Flashcard with the given ID in the array list. Retrieves the question attribute of the found flashcard",
            summary = "Gets the Question of the flashcard with the specified ID."
    )
    @GetMapping("/question/{id}")
    public ResponseEntity<Object> getQuestionByID(@PathVariable("id") UUID id) { return ResponseHandler.responseBuilder("Here is the requested question with id: " + id.toString(), HttpStatus.OK, flashcardServiceImp.getQuestion(id));}



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Uses a linear search algorithm to find the specific Flashcard with the given ID in the array list. Retrieves the answer attribute of the found flashcard",
            summary = "Gets the Answer of the flashcard with the specified ID."
    )
    @GetMapping("/answer/{id}")
    public ResponseEntity<Object> getAnswerByID(@PathVariable("id") UUID id) { return ResponseHandler.responseBuilder("Here is the requested answer with ID: " + id.toString(), HttpStatus.OK, flashcardServiceImp.getAnswer(id));}



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Uses a linear sort algorithm to find the Flashcard objects with the given difficulty. Each Flashcard with the given difficulty is appended to a new array list. API returns this list.",
            summary = "Gets all Flashcards with the specified difficulty in URI."
    )
    @GetMapping("/difficulty/{difficulty}")
    public ResponseEntity<Object> getFlashcardsByDifficulty(@PathVariable("difficulty") String difficulty) {
        return ResponseHandler.responseBuilder("Here are the flashcards with the provided difficulty (%s).".formatted(difficulty), HttpStatus.OK, flashcardServiceImp.getFlashcardsByDifficulty(difficulty));
    }



    @Operation(
            description = "Reads the JSON file of flashcards into an array list of Flashcard objects. Uses a linear sort algorithm to find the Flashcard objects with the given topic. Each Flashcard with the given topic is appended to a new array list. API returns this list.",
            summary = "Gets all Flashcards with the specified topic in URI."
    )
    @GetMapping("/topic/{topic}")
    public ResponseEntity<Object> getFlashcardsByTopic(@PathVariable("topic") String topic) {
        return ResponseHandler.responseBuilder("Here are the flashcards with the provided topic (%s).".formatted(topic), HttpStatus.OK, flashcardServiceImp.getFlashcardsByTopic(topic));
     }



    @Operation(
            description = "Reads the JSON file of stored flashcards into an array list of Flashcard objects. The new Flashcard object is added the arraylist and the JSON file is overwritten with the updated array list of Flashcard objects.",
            summary = "Adds a new Flashcard"
    )
    @PostMapping(path="/new", produces="application/json")
    public String createFlashcard(@Valid @RequestBody Flashcard flashcard) {
        flashcardServiceImp.addFlashcard(flashcard);
        return "Flashcard created successfully with ID: %s ".formatted(flashcard.getID());
    }



    @Operation(
            description = "Reads the JSON file of stored flashcards into an array list of Flashcard objects. Iterates through this list to find a Flashcard object with the same ID as the flashcard provided by the client. Updates all attributes of the stored flashcard with the values provided by the client and then overwrite the JSON file with the updated array list.",
            summary = "Updates a stored flashcard"
    )
    @PutMapping(path = "/update", produces = "application/json")
    public String updateFlashcard(@Valid @RequestBody Flashcard flashcard) {
        flashcardServiceImp.updateFlashcard(flashcard);
        return "Flashcard updated successfully with ID: %s ".formatted(flashcard.getID());
    }



    @Operation(
            description = "Reads the JSON file of stored flashcards into an array list of Flashcard objects. Iterates through this list to find a Flashcard object with the same ID as provided. Removes this flashcard from the array list and then overwrites JSON file with the updated array list.",
            summary = "Deletes a stored flashcard"
    )
    @DeleteMapping("/delete/{id}")
    public String deleteFlashcardByID(@PathVariable("id") UUID id) {
        flashcardServiceImp.removeFlashcard(id);
        return "Flashcard (ID:%s) deleted successfully.".formatted(id);
    }

}
