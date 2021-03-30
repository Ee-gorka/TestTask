package by.duallab.testtask.bus;

import java.time.Duration;
import java.time.LocalTime;

public class Bus implements Comparable<Bus> {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final String SERVICE_POSH_NAME = "Posh";
    private String serviceName;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private long duration;

    public Bus(String serviceName, LocalTime departureTime, LocalTime arrivalTime) {
        this.serviceName = serviceName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.duration = calculateDuration(departureTime, arrivalTime);
    }

    private long calculateDuration(LocalTime departureTime, LocalTime arrivalTime) {
        long tmpDuration = Duration.between(departureTime, arrivalTime).toMinutes();
        if (tmpDuration < 0) return MINUTES_IN_HOUR * HOURS_IN_DAY + tmpDuration;
        return tmpDuration;
    }

    public String getServiceName() {
        return serviceName;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public int compareTo(Bus bus) {
        if (this.equals(bus)) {
            return -1;
        }
        if (this.getDepartureTime().equals(bus.getDepartureTime())) {
            if (this.getDuration() < bus.getDuration()) {
                return 1;
            } else if (this.getDuration() == bus.getDuration()) {
                if (this.serviceName.equals(SERVICE_POSH_NAME)) return 1;
                return -1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
