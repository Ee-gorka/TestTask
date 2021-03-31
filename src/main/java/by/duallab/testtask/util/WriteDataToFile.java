package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;
import by.duallab.testtask.bus.BusService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import static by.duallab.testtask.app.ApplicationConstants.*;

public class WriteDataToFile {

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
