package by.duallab.testtask.bus;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BusTest {
    @Test
    public void checkEqualsBus(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        assertEquals(-1,firstBus.compareTo(secondBus));
    }
    @Test
    public void checkBusesWithSameTimetableButDifferentServiceName(){
        Bus firstBus = new Bus("Posh",LocalTime.of(10,10),LocalTime.of(11,0));
        Bus secondBus = new Bus("Grotty",LocalTime.of(10,10),LocalTime.of(11,0));
        assertEquals(1,firstBus.compareTo(secondBus));
    }
}