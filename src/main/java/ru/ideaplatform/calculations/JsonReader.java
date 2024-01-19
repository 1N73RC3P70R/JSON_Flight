package ru.ideaplatform.calculations;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonReader {
    public static JsonObject readJsonFromFile(String fileName) throws FileNotFoundException {

        Gson gson = new Gson();
        JsonObject flightPath = gson.fromJson(new FileReader("tickets.json"), JsonObject.class);
        return flightPath;
    }

}
