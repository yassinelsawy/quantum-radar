package radar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String file = args.length > 0 ? args[0] : "radar/observations.csv";

        Radar radar = new Radar();
        radar.addRule(new SpeedRule(150));
        radar.addRule(new SeatbeltRule(100));

        List<Observation> observations = readObservations(file);

        System.out.println("=== Fines issued ===");
        for (Observation obs : observations) {
            Fine fine = radar.inspect(obs);
            if (fine != null) {
                System.out.println(fine);
            }
        }

        System.out.println("\n=== Total owed per plate ===");
        for (Map.Entry<String, Integer> e : radar.getFinesByPlate().entrySet()) {
            System.out.printf("%-10s $%d%n", e.getKey(), e.getValue());
        }

        System.out.println("\n=== Violations by rule ===");
        for (Map.Entry<String, Integer> e : radar.getViolationCounts().entrySet()) {
            System.out.printf("%-10s %d%n", e.getKey(), e.getValue());
        }
    }

    private static List<Observation> readObservations(String file) {
        List<Observation> observations = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Path.of(file))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }
                String[] parts = line.split(",");
                if (firstLine && parts[3].trim().equalsIgnoreCase("speed")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                String plate = parts[0].trim();
                LocalDate date = LocalDate.parse(parts[1].trim());
                CarType carType = CarType.fromName(parts[2]);
                int speed = Integer.parseInt(parts[3].trim());
                boolean seatbelt = Boolean.parseBoolean(parts[4].trim());

                observations.add(new Observation(plate, date, carType, speed, seatbelt));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read observations from " + file, e);
        }
        return observations;
    }
}
