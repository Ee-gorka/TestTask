package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;
import by.duallab.testtask.bus.BusService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class WriteDataToFile {
    private static final String PATH_TO_OUTPUT_FILE = "src/output.txt";
    public static final String LF = "\n";
    private static final String SERVICE_GROTTY_NAME = "Grotty";
    private static final String SERVICE_POSH_NAME = "Posh";

    public static void printDataToFile(BusService busService) {
        LinkedList<Bus> busesToPrint = busService.getBuses();
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(PATH_TO_OUTPUT_FILE))) {
            for (Bus bus : busesToPrint) {
                if (bus.getServiceName().equals(SERVICE_POSH_NAME)) {
                    writerToFile.write(bus.toString() + LF);
                }
            }
            writerToFile.write(LF);
            for (Bus bus : busesToPrint) {
                if (bus.getServiceName().equals(SERVICE_GROTTY_NAME)) {
                    writerToFile.write(bus.toString() + LF);
                }
            }
        } catch (IOException e) {
            System.out.println("Problem with output file!");
        }
    }
}
