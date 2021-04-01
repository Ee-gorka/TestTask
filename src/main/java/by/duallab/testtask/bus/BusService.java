package by.duallab.testtask.bus;

import java.util.*;

public class BusService {
    private final ArrayList<Bus> buses;

    public BusService(ArrayList<Bus> buses) {
        this.buses = buses;
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public void deleteUselessBuses() {
        buses.sort(Comparator.comparing(Bus::getDepartureTime));
        for (int i = 1; i < buses.size(); ) {
            int isBetter = buses.get(i).compareBuses(buses.get(i - 1));
            if (isBetter > 0) {
                buses.remove(i - 1);
                i = Integer.max(1, i - 1);
            } else if (isBetter < 0) {
                buses.remove(i);
            } else {
                ++i;
            }
        }
        checkLastBuses();
    }

    public void checkLastBuses() {
        int i = buses.size() - 1;
        if (buses.size() >= 2) {
            while (buses.get(i).getDepartureTime().getHour() == 23) {
                int isBetter = buses.get(0).compareBuses(buses.get(i));
                if (isBetter > 0) {
                    buses.remove(buses.size() - 1);
                    if (i > 1) --i;
                } else if (isBetter < 0) {
                    buses.remove(0);
                }
            }
        }
    }
}

