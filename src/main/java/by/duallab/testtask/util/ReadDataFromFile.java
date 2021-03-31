package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.LinkedList;

import static by.duallab.testtask.app.ApplicationConstants.SERVICE_GROTTY_NAME;
import static by.duallab.testtask.app.ApplicationConstants.SERVICE_POSH_NAME;

public class ReadDataFromFile {

    public static LinkedList<Bus> readBusesFromFile(String inputFilePath) {
        LinkedList<Bus> buses = new LinkedList<>();
        try (BufferedReader readerFromFile = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            Bus bus;
            while ((line = readerFromFile.readLine()) != null) {
                bus = parseData(line);
                if (bus != null && bus.getDuration() < 60) {
                    buses.add(bus);
                }
            }
        } catch (IOException e) {
            System.out.println("Incorrect file path!");
        }
        return buses;
    }

    public static Bus parseData(String line) {
        String delimiters = "[ :]+";
        String[] tokens = line.split(delimiters);
        try {
            String serviceName = tokens[0];
            if (!serviceName.equals(SERVICE_POSH_NAME) && !serviceName.equals(SERVICE_GROTTY_NAME)) {
                throw new IllegalArgumentException();
            }
            int departureHours = Integer.parseInt(tokens[1]), departureMinutes = Integer.parseInt(tokens[2]),
                    arrivalHours = Integer.parseInt(tokens[3]), arrivalMinutes = Integer.parseInt(tokens[4]);
            LocalTime departureTime = LocalTime.of(departureHours, departureMinutes);
            LocalTime arrivalTime = LocalTime.of(arrivalHours, arrivalMinutes);
            return new Bus(serviceName, departureTime, arrivalTime);
        } catch (DateTimeException | IllegalArgumentException ex) {
            System.out.println("Wrong data format!This entry will be ignored!(" + line + ")");
        }
        return null;
    }
}
