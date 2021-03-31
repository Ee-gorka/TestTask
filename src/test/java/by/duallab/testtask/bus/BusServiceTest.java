package by.duallab.testtask.bus;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;


import static org.junit.jupiter.api.Assertions.*;

class BusServiceTest {

    @Test
    void checkArrayWithOneElement() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(10, 10), LocalTime.of(11, 0)));
        BusService busService = new BusService(busesForCheck);
        assertEquals(1, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(10, 10), LocalTime.of(11, 0)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(10, 10), LocalTime.of(11, 0)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(10, 30), LocalTime.of(11, 0)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(12, 50), LocalTime.of(13, 20)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses2() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(10, 10), LocalTime.of(11, 0)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(10, 20), LocalTime.of(11, 20)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(10, 30), LocalTime.of(10, 55)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(12, 50), LocalTime.of(13, 20)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses3() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(0, 10), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(10, 20), LocalTime.of(11, 20)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(23, 40), LocalTime.of(0, 35)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses4() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(0, 10), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 20), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 25), LocalTime.of(0, 35)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses5() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(0, 10), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 20), LocalTime.of(0, 50)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 25), LocalTime.of(0, 30)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(1, busService.getBuses().size());
    }

    @Test
    void checkDeletingUselessBuses6() {
        ArrayList<Bus> busesForCheck = new ArrayList<>();
        busesForCheck.add(new Bus("Grotty",
                LocalTime.of(0, 10), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 20), LocalTime.of(0, 30)));
        busesForCheck.add(new Bus("Posh",
                LocalTime.of(0, 25), LocalTime.of(0, 35)));
        BusService busService = new BusService(busesForCheck);
        busService.deleteUselessBuses();
        assertEquals(2, busService.getBuses().size());
    }
}