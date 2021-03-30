package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.LinkedList;

public class ReadDataFromFile {
    private static final String SERVICE_GROTTY_NAME = "Grotty";
    private static final String SERVICE_POSH_NAME = "Posh";

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
