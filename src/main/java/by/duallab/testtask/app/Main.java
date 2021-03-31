package by.duallab.testtask.app;

import by.duallab.testtask.bus.BusService;
import by.duallab.testtask.util.ReadDataFromFile;
import by.duallab.testtask.util.WriteDataToFile;

import java.util.Scanner;

import static by.duallab.testtask.app.ApplicationConstants.PATH_TO_OUTPUT_FILE;

public class Main {
    public static void main(String[] args) {
        System.out.println("Enter path to input file:");
        String filePath;
        try (Scanner in = new Scanner(System.in)) {
            filePath = in.nextLine();
        }
        BusService busService = new BusService(ReadDataFromFile.readBusesFromFile(filePath));
        if (!busService.getBuses().isEmpty()) {
            busService.deleteUselessBuses();
            WriteDataToFile.printDataToFile(busService);
            System.out.println("Timetable was optimized and written to file (" + PATH_TO_OUTPUT_FILE + ")");
        } else {
            System.out.println("Input file was empty or all of the buses are useless!");
        }
    }
}
