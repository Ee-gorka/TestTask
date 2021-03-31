package by.duallab.testtask.bus;

import java.util.LinkedList;

public class BusService {
    private LinkedList<Bus> buses;

    public BusService(LinkedList<Bus> buses) {
        this.buses = buses;
    }

    public LinkedList<Bus> getBuses() {
        return buses;
    }
    public void deleteUselessBuses(){
        for (int i = 1; i < buses.size(); ) {
            int isBetter = buses.get(i).compareTo(buses.get(i - 1));
            if (isBetter > 0) {
                buses.remove(i - 1) ;
                i = Integer.max(1, i - 1);
            } else if (isBetter < 0) {
                buses.remove(i);
            } else {
                ++i;
            }
        }
    }
}
