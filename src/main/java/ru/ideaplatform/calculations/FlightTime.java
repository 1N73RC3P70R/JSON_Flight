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
    Map<String, String> minFlightTime = new HashMap<>();
    Map<String, String> maxFlightTime = new HashMap<>();

    public void flightTime(JsonArray ticketsArray, String cityOne, String cityTwo) {
        for (JsonElement ticketsElement : ticketsArray) {
            JsonObject ticketObject = ticketsElement.getAsJsonObject();

            String departureCity = ticketObject.get("origin_name").getAsString();
            String arrivalCity = ticketObject.get("destination_name").getAsString();

            if ((departureCity.equals(cityOne) && arrivalCity.equals(cityTwo)) ||
                    (departureCity.equals(cityTwo) && arrivalCity.equals(cityOne))) {

                String carrier = ticketObject.get("carrier").getAsString();
                double time = flightTimeCalculation(ticketObject);

                int hours = (int) time;
                int minutes = (int) ((time - hours) * 60);

                String formattedTime = String.format("%d ч %02d мин", hours, minutes);

                if (!minFlightTime.containsKey(carrier) || formattedTime.compareTo(minFlightTime.get(carrier)) < 0) {
                    minFlightTime.put(carrier, formattedTime);
                }
                if (!maxFlightTime.containsKey(carrier) || formattedTime.compareTo(maxFlightTime.get(carrier)) > 0) {
                    maxFlightTime.put(carrier, formattedTime);
                }
            }
        }
    }

    static double flightTimeCalculation(JsonObject ticketsObject) {
        SimpleDateFormat date = new SimpleDateFormat("dd.MM.yy HH:mm", new Locale("ru", "RU"));

        try {
            Date departureTime = date.parse(ticketsObject.get("departure_date").getAsString() + " " + ticketsObject.get("departure_time").getAsString());
            Date arrivalTime = date.parse(ticketsObject.get("arrival_date").getAsString() + " " + ticketsObject.get("arrival_time").getAsString());
            double result = arrivalTime.getTime() - departureTime.getTime();
            return result / (60.0 * 60.0 * 1000.0);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getMinFlightTime() {
        return minFlightTime;
    }

    public Map<String, String> getMaxFlightTime() {
        return maxFlightTime;
    }
}
