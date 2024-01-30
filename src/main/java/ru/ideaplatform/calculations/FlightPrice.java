package ru.ideaplatform.calculations;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightPrice {
    public static double flightPrice(String jsonFilePath) {
        return calculateFlightPrices(jsonFilePath);
    }

    public static double calculateFlightPrices(String jsonFilePath) {
        List<Integer> prices = extractPricesFromJson(jsonFilePath);

        if (prices.isEmpty()) {
            return 0.0;
        }

        double averagePrice = calculateAveragePrice(prices);
        double medianPrice = calculateMedianPriceVladivostok(prices);


        System.out.println("Средняя цена: " + averagePrice);
        System.out.println("Медианная цена: " + medianPrice);

        ToText.toFile(Collections.emptyMap(), Collections.emptyMap(), averagePrice - medianPrice, averagePrice, medianPrice);

        return averagePrice - medianPrice;
    }

    private static List<Integer> extractPricesFromJson(String jsonFilePath) {
        List<Integer> prices = new ArrayList<>();

        try (FileReader reader = new FileReader(jsonFilePath)) {
            JsonArray ticketsArray = JsonParser.parseReader(reader).getAsJsonObject().getAsJsonArray("tickets");

            for (JsonElement ticketElement : ticketsArray) {
                JsonObject ticketObject = ticketElement.getAsJsonObject();
                String origin = ticketObject.get("origin").getAsString();
                String destination = ticketObject.get("destination").getAsString();

                if ("VVO".equals(origin) && "TLV".equals(destination)) {
                    int price = ticketObject.get("price").getAsInt();
                    prices.add(price);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return prices;
    }

    public static double calculateMedianPriceVladivostok(List<Integer> prices) {
        Collections.sort(prices);
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }

    public static double calculateAveragePrice(List<Integer> prices) {
        return prices.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}