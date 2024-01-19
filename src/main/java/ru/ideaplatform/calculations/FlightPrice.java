package ru.ideaplatform.calculations;

import java.util.Collections;
import java.util.List;

public class FlightPrice {
    public static double flightPrice(List<Integer> prices) {
        double averagePrice = calculateAveragePrice(prices);
        double medianPrice = calculateMedianPrice(prices);
        return averagePrice - medianPrice;
    }

    private static double calculateMedianPrice(List<Integer> prices) {
        Collections.sort(prices);
        int size = prices.size();
        if (size % 2 == 0) {
            return (prices.get(size / 2 - 1) + prices.get(size / 2)) / 2.0;
        } else {
            return prices.get(size / 2);
        }
    }

    private static double calculateAveragePrice(List<Integer> prices) {
        return prices.stream().mapToInt(Integer::intValue).average().orElse(0.0);
    }
}


