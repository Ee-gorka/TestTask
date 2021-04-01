package by.duallab.testtask.bus;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    @Test
    public void checkEqualsBus(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        assertEquals(1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithSameTimetableButDifferentServiceName(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Grotty",LocalTime.of(10,10),LocalTime.of(11,0));
        assertEquals(1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithSameDepartureTime(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Grotty",LocalTime.of(10,10),LocalTime.of(10,50));
        assertEquals(-1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithSameDepartureTime2(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(10,40));
        Bus secondBus = new Bus("Grotty",LocalTime.of(10,10),LocalTime.of(10,50));
        assertEquals(1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithSameArrivalTime(){
        Bus firstBus = new Bus("Posh",LocalTime.of(23,51),LocalTime.of(0,50));
        Bus secondBus = new Bus("Grotty",LocalTime.of(0,10),LocalTime.of(0,50));
        assertEquals(-1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithSameArrivalTime2(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Grotty",LocalTime.of(10,5),LocalTime.of(11,0));
        assertEquals(1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithLaterDepartureTimeAndEarlierArrivalTime(){
        Bus firstBus = new Bus("Posh",LocalTime.of(5,5),LocalTime.of(6,0));
        Bus secondBus = new Bus("Grotty",LocalTime.of(5,20),LocalTime.of(5,50));
        assertEquals(-1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithLaterDepartureTimeAndEarlierArrivalTime2(){
        Bus firstBus = new Bus("Posh",LocalTime.of(23,50),LocalTime.of(0,30));
        Bus secondBus = new Bus("Grotty",LocalTime.of(0,0),LocalTime.of(0,25));
        assertEquals(-1,firstBus.compareBuses(secondBus));
    }
    @Test
    public void checkBusesWithLaterDepartureTimeAndEarlierArrivalTime3(){
        Bus firstBus = new Bus("Posh",LocalTime.of(23,45),LocalTime.of(0,20));
        Bus secondBus = new Bus("Grotty",LocalTime.of(23,40),LocalTime.of(5,30));
        assertEquals(1,firstBus.compareBuses(secondBus));
    }
}