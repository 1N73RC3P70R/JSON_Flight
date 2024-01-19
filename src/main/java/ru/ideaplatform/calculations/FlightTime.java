package ru.ideaplatform.calculations;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FlightTime {
    Map<String, Integer> minFlightTime = new HashMap<String, Integer>();
    Map<String, Integer> maxFlightTime = new HashMap<String, Integer>();

    public void flightTime(JsonArray ticketsArray) {

        for (JsonElement ticketsElement : ticketsArray) {

            JsonObject ticketObject = ticketsElement.getAsJsonObject();
            String carrier = ticketObject.get("carrier").getAsString();
            String stops = ticketObject.get("stops").getAsString();
            int time = (int) flightTimeCalculation(ticketObject);

            if (!minFlightTime.containsKey(carrier) || time < minFlightTime.get(carrier)) {
                minFlightTime.put(carrier, time);
            }
            if (!maxFlightTime.containsKey(carrier) || time > maxFlightTime.get(carrier)) {
                maxFlightTime.put(carrier, time);
            }
        }
    }

    static double flightTimeCalculation(JsonObject ticketsObject) {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yy HH:mm", new Locale("ru", "RU"));

        try {
            Date departureTime = date.parse(ticketsObject.get("departure_date").getAsString() + " " + ticketsObject.get("departure_time").getAsString());
            Date arrivalTime = date.parse(ticketsObject.get("arrival_date").getAsString() + " " + ticketsObject.get("arrival_time").getAsString());
            double result = arrivalTime.getTime() - departureTime.getTime();
            return (result / (60 * 60 * 1000));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public Map<String, Integer> getMinFlightTime() {
        return minFlightTime;
    }

    public Map<String, Integer> getMaxFlightTime() {
        return maxFlightTime;
    }
}
