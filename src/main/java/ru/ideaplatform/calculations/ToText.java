package ru.ideaplatform.calculations;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ToText {
    public static void writeResultsToFile(Map<String, Integer> minFlightTimes, Map<String, Integer> maxFlightTimes, double priceDifference) {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream("билет.txt"))) {
            writer.write("\n");
            writer.write(logo() + ascii());


            writer.write("Минимальное время перелёта (час.):");
            writer.write("\n");
            for (Map.Entry<String, Integer> entry : minFlightTimes.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.write("\n");
            }

            writer.write("\n");
            writer.write("Максимальное время перелёта (час.):");
            writer.write("\n");
            for (Map.Entry<String, Integer> entry : maxFlightTimes.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.write("\n");
            }

            writer.write("\n");
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
