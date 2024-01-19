package ru.ideaplatform.calculations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ToText {
    public static void writeResultsToFile(Map<String, Integer> minFlightTimes, Map<String, Integer> maxFlightTimes, double priceDifference) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("билет.txt"))) {
            writer.newLine();
            writer.write(logo() + ascii());


            writer.write("Минимальное время перелёта (час.):");
            writer.newLine();
            for (Map.Entry<String, Integer> entry : minFlightTimes.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Максимальное время перелёта (час.):");
            writer.newLine();
            for (Map.Entry<String, Integer> entry : maxFlightTimes.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }

            writer.newLine();
            writer.write("Разница между средней ценой и медианой: " + priceDifference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String logo(){
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
                """;}

  public static String ascii(){
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
