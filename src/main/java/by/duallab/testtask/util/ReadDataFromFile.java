package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static by.duallab.testtask.app.ApplicationConstants.*;

public class ReadDataFromFile {

    public static ArrayList<Bus> readBusesFromFile(String inputFilePath) {
        ArrayList<Bus> buses = new ArrayList<>();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            Bus bus;
            while ((line = readerFromFile.readLine()) != null) {
                Optional<Bus> result = parseData(line);
                if (result.isPresent()) {
                    bus = result.get();
                    if (bus.getDuration() <= 60) {
                        buses.add(bus);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Incorrect file path!");
        }
        return buses;
    }

    public static Optional<Bus> parseData(String line) {
        String[] tokens = line.split(DELIMITERS);
        try {
            String serviceName = tokens[0];
            if (!serviceName.equals(SERVICE_POSH_NAME) && !serviceName.equals(SERVICE_GROTTY_NAME)) {
                throw new IllegalArgumentException();
            }
            int departureHours = Integer.parseInt(tokens[1]);
            int departureMinutes = Integer.parseInt(tokens[2]);
            int arrivalHours = Integer.parseInt(tokens[3]);
            int arrivalMinutes = Integer.parseInt(tokens[4]);
            LocalTime departureTime = LocalTime.of(departureHours, departureMinutes);
            LocalTime arrivalTime = LocalTime.of(arrivalHours, arrivalMinutes);
            return Optional.of(new Bus(serviceName, departureTime, arrivalTime));
        } catch (DateTimeException | IllegalArgumentException ex) {
            String errorMessage = "Wrong data!This entry will be ignored!(%s)";
            System.err.println(String.format(errorMessage, line));
        }
        return Optional.empty();
    }
}
