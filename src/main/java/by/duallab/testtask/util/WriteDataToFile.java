package by.duallab.testtask.util;

import by.duallab.testtask.bus.Bus;
import by.duallab.testtask.bus.BusService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static by.duallab.testtask.app.ApplicationConstants.*;

public class WriteDataToFile {

    public static void printDataToFile(BusService busService) {
        ArrayList<Bus> busesToPrint = busService.getBuses();
        boolean[] masks = busService.getMasksOfBuses();
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(PATH_TO_OUTPUT_FILE))) {
            for (int i = 0; i < masks.length; ++i) {
                if(masks[i]&&busesToPrint.get(i).getServiceName().equals(SERVICE_POSH_NAME)){
                    writerToFile.write(busesToPrint.get(i).toString() + LF);
                }
            }
            writerToFile.write(LF);
            for (int i = 0; i < masks.length; ++i) {
                if(masks[i]&&busesToPrint.get(i).getServiceName().equals(SERVICE_GROTTY_NAME)){
                    writerToFile.write(busesToPrint.get(i).toString() + LF);
                }
            }
        } catch (IOException e) {
            System.out.println("Problem with output file!");
        }
    }
}
