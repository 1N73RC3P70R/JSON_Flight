package ru.ideaplatform;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.ideaplatform.calculations.FlightPrice;
import ru.ideaplatform.calculations.FlightTime;
import ru.ideaplatform.calculations.JsonReader;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.ideaplatform.calculations.ToText.toFile;

public class Main {
    public static void main(String[] args) {
        try {
            JsonObject flight = JsonReader.readJsonFromFile("tickets.json");
            JsonArray ticketsArray = flight.getAsJsonArray("tickets");

            FlightTime flightTime = new FlightTime();
            flightTime.flightTime(ticketsArray, "Владивосток", "Тель-Авив");

            Map<String, Duration> minFlightTimes = flightTime.getMinFlightTime();
            Map<String, Duration> maxFlightTimes = flightTime.getMaxFlightTime();

            List<Integer> prices = new ArrayList<>();
            for (JsonElement ticketElement : ticketsArray) {
                prices.add(ticketElement.getAsJsonObject().get("price").getAsInt());
            }

            double priceDifference = FlightPrice.flightPrice(prices);

            toFile(minFlightTimes, maxFlightTimes, priceDifference);

            System.out.println("Результаты находятся в \"билет.txt\"");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

