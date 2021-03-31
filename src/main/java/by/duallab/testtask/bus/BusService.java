package by.duallab.testtask.bus;

import java.util.*;

public class BusService {
    private final ArrayList<Bus> buses;
    private boolean[] masksOfBuses;

    public BusService(ArrayList<Bus> buses) {
        this.buses = buses;
        this.masksOfBuses = new boolean[buses.size()];
        Arrays.fill(masksOfBuses, true);
    }

    public ArrayList<Bus> getBuses() {
        return buses;
    }

    public boolean[] getMasksOfBuses() {
        return masksOfBuses;
    }

    public void markUselessBuses() {
        buses.sort(Comparator.comparing(Bus::getDepartureTime));
        for (int i = 1; i < buses.size(); ) {
            if (masksOfBuses[i] && masksOfBuses[i - 1]) {
                int isBetter = buses.get(i).compareTo(buses.get(i - 1));
                if (isBetter > 0) {
                    masksOfBuses[i - 1] = false;
                } else if (isBetter < 0) {
                    masksOfBuses[i] = false;
                } else {
                    ++i;
                }
            }
            ++i;
        }
    }

}
