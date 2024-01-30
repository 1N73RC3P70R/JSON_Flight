package ru.ideaplatform.calculations;

import java.io.*;
import java.time.Duration;
import java.util.Map;

public class ToText {
    public static void toFile(Map<String, Duration> minFlightTimes, Map<String, Duration> maxFlightTimes, double priceDifference, double averagePrice, double medianPrice) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("билет.txt"))) {
            writer.write(logo());
            writer.write(ascii());

            writer.write("Минимальное время полета по авиакомпаниям:\n");
            for (Map.Entry<String, Duration> entry : minFlightTimes.entrySet()) {
                String carrier = entry.getKey();
                Duration duration = entry.getValue();
                writer.write(carrier + ": " + formatDuration(duration) + "\n");
            }

            writer.write("\nМаксимальное время полета по авиакомпаниям:\n");
            for (Map.Entry<String, Duration> entry : maxFlightTimes.entrySet()) {
                String carrier = entry.getKey();
                Duration duration = entry.getValue();
                writer.write(carrier + ": " + formatDuration(duration) + "\n");
            }

            writer.write("\nРазница в цене: " + priceDifference);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        return String.format("%02d:%02d", hours, minutes);
    }


    public static String logo() {
        return """
                                                                                               \s
                                                                                               \s
                                                                                               \s
                                                                                               \s
                                                    *******                                    \s
                                                   ********.                                   \s
                                                    ,*****.             ,**                    \s
                                                                  ,*****                       \s
                                                           .**********                         \s
                                                  .,****************                           \s
                                   ..,****************************                             \s
                                                       *********                               \s
                                                      ********                                 \s
                                                     .*******                                  \s
                                                     ******.                                   \s
                                                    ******                                     \s
                                                    *****                                      \s
                                                   ****,                                       \s
                                                   ***,                                        \s
                                                  ***.                                         \s
                                                 .**.                                          \s
                                                 **,                                           \s
                                                 **                                            \s
                                                **                                             \s
                                                *                                              \s
                                               ,.                                              \s
                                               *                                               \s
                                              .                                                \s
                                              *                                                \s
                                                                                               \s
                                                                                               \s
                                                                                               \s
                """;
    }

    public static String ascii() {
        return """
                 _____ _____  ______                 _____  _            _______ ______ ____  _____  __  __\s
                |_   _|  __ \\|  ____|   /\\          |  __ \\| |        /\\|__   __|  ____/ __ \\|  __ \\|  \\/  |
                  | | | |  | | |__     /  \\         | |__) | |       /  \\  | |  | |__ | |  | | |__) | \\  / |
                  | | | |  | |  __|   / /\\ \\        |  ___/| |      / /\\ \\ | |  |  __|| |  | |  _  /| |\\/| |
                 _| |_| |__| | |____ / ____ \\       | |    | |____ / ____ \\| |  | |   | |__| | | \\ \\| |  | |
                |_____|_____/|______/_/    \\_\\      |_|    |______/_/    \\_\\_|  |_|    \\____/|_|  \\_\\_|  |_|
                                                                                                           \s
                                                                                                           \s
                                                                                                           """;
    }
}
