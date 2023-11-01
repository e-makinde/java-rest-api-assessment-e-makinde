package com.cbfacademy.apiassessment;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import com.cbfacademy.apiassessment.flashcard.Flashcard;
import com.google.gson.Gson;

public class JSONFileHandler {
    public static void writeFile(Flashcard flashcard, String filename) {                
        try {
            Gson gson = new Gson();
            gson.toJson(flashcard, new FileWriter(filename));
        } catch (IOException e) {

        }
    }


    public static List<Flashcard> readFile(String filename) {
        List<Flashcard> flashcards;

        try {
            Reader json = new FileReader(filename);
            Gson gson = new Gson();
            gson.fromJson(json, Flashcard.class);
        } catch (IOException e) {

        }

    }
            
}
