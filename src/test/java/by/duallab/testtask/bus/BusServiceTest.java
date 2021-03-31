package by.duallab.testtask.bus;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class BusServiceTest {

    @Test
    void checkArrayWithOneElement() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        Bus bus = new Bus("Grotty", LocalTime.of(10, 10), LocalTime.of(11, 0));
        busesForCheck.add(bus);
        BusService busService = new BusService(busesForCheck);
        assertEquals(1, busService.getBuses().size());
    }
    @Test
    void checkMarkingUselessBuses() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        Bus bus = new Bus("Grotty", LocalTime.of(10, 10), LocalTime.of(11, 0));
        Bus bus1 = new Bus("Posh", LocalTime.of(10, 10), LocalTime.of(11, 0));
        Bus bus2 = new Bus("Posh", LocalTime.of(10, 30), LocalTime.of(11, 0));
        Bus bus3 = new Bus("Posh", LocalTime.of(12, 50), LocalTime.of(13, 20));
        busesForCheck.add(bus);busesForCheck.add(bus1);busesForCheck.add(bus2);busesForCheck.add(bus3);
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }
    @Test
    void checkMarkingUselessBuses2() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        Bus bus = new Bus("Grotty", LocalTime.of(10, 10), LocalTime.of(11, 0));
        Bus bus1 = new Bus("Posh", LocalTime.of(10, 20), LocalTime.of(11, 20));
        Bus bus2 = new Bus("Posh", LocalTime.of(10, 30), LocalTime.of(10, 55));
        Bus bus3 = new Bus("Posh", LocalTime.of(12, 50), LocalTime.of(13, 20));
        busesForCheck.add(bus);busesForCheck.add(bus1);busesForCheck.add(bus2);busesForCheck.add(bus3);
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }
}