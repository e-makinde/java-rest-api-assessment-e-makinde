package com.cbfacademy.apiassessment.repository;

import com.cbfacademy.apiassessment.model.Flashcard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.util.ArrayList;

@Repository
public class flashcardRepository {
    String filename = "src/main/java/com/cbfacademy/apiassessment/data/Flashcards.json";

    public void writeJSONFile(ArrayList<Flashcard> flashcards) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(flashcards, writer);
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public @NotNull
    @Unmodifiable ArrayList<Flashcard> readJSONFile(){
        ArrayList<Flashcard> flashcards = new ArrayList<>();
        Gson gson = new GsonBuilder().create();

        try(Reader reader = new FileReader(filename)) {
            flashcards = gson.fromJson(reader, new TypeToken<ArrayList<Flashcard>>() {
            }.getType());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flashcards;
    }
}
