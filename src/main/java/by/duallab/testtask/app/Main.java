package by.duallab.testtask.app;

import by.duallab.testtask.bus.BusService;
import by.duallab.testtask.util.ReadDataFromFile;

import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        in.close();
        BusService busService = new BusService(ReadDataFromFile.readBusesFromFile(filePath));

    }
}
