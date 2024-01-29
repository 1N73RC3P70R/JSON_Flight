package ru.ideaplatform.calculations;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FlightTime {
    Map<String, Duration> minFlightTime = new HashMap<>();
    Map<String, Duration> maxFlightTime = new HashMap<>();

    public void flightTime(JsonArray ticketsArray, String cityOne, String cityTwo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm", new Locale("ru", "RU"));

        for (JsonElement ticketsElement : ticketsArray) {
            JsonObject ticketObject = ticketsElement.getAsJsonObject();

            String departureCity = ticketObject.get("origin_name").getAsString();
            String arrivalCity = ticketObject.get("destination_name").getAsString();

            if ((departureCity.equals(cityOne) && arrivalCity.equals(cityTwo)) ||
                    (departureCity.equals(cityTwo) && arrivalCity.equals(cityOne))) {

                String carrier = ticketObject.get("carrier").getAsString();
                String departureTimeString = ticketObject.get("departure_time").getAsString();
                String arrivalTimeString = ticketObject.get("arrival_time").getAsString();

                LocalTime departureTime = LocalTime.parse(departureTimeString, formatter);
                LocalTime arrivalTime = LocalTime.parse(arrivalTimeString, formatter);

                Duration duration = Duration.between(departureTime, arrivalTime);

                if (!minFlightTime.containsKey(carrier) || duration.compareTo(minFlightTime.get(carrier)) < 0) {
                    minFlightTime.put(carrier, duration);
                }
                if (!maxFlightTime.containsKey(carrier) || duration.compareTo(maxFlightTime.get(carrier)) > 0) {
                    maxFlightTime.put(carrier, duration);
                }
            }
        }
    }

    public Map<String, Duration> getMinFlightTime() {
        return minFlightTime;
    }

    public Map<String, Duration> getMaxFlightTime() {
        return maxFlightTime;
    }
}