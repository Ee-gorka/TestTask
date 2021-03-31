package by.duallab.testtask.bus;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private static final int MINUTES_IN_HOUR = 60;
    private static final int HOURS_IN_DAY = 24;
    private static final String SERVICE_POSH_NAME = "Posh";
    private String serviceName;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private long duration;
    private boolean arrivingAfterMidnight = false;

    public Bus(String serviceName, LocalTime departureTime, LocalTime arrivalTime) {
        this.serviceName = serviceName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        if (arrivalTime.isBefore(departureTime)) arrivingAfterMidnight = true;
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

    public boolean isArrivingAfterMidnight() {
        return arrivingAfterMidnight;
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
                if (this.getServiceName().equals(SERVICE_POSH_NAME)) return 1;
                return -1;
            } else {
                return -1;
            }
        }
        if (this.getArrivalTime().equals(bus.getArrivalTime())) {
            if (this.getDuration() < bus.getDuration()) {
                return 1;
            } else if (this.getDuration() > bus.getDuration()) {
                return -1;
            }
        }
        if (this.isArrivingAfterMidnight() != bus.isArrivingAfterMidnight()) {
            if (this.getDepartureTime().minusHours(1).isBefore(bus.getDepartureTime().minusHours(1)) &&
                    this.getArrivalTime().minusHours(1).isAfter(bus.getArrivalTime().minusHours(1))) {
                return -1;
            } else if (this.getDepartureTime().minusHours(1).isAfter(bus.getDepartureTime().minusHours(1)) &&
                    this.getArrivalTime().minusHours(1).isBefore(bus.getArrivalTime().minusHours(1))) {
                return 1;
            }
        } else {
            if (this.getDepartureTime().isBefore(bus.getDepartureTime()) &&
                    this.getArrivalTime().isAfter(bus.getArrivalTime())) {
                return -1;
            } else if (this.getDepartureTime().isAfter(bus.getDepartureTime()) &&
                    this.getArrivalTime().isBefore(bus.getArrivalTime())) {
                return 1;
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return duration == bus.duration &&
                arrivingAfterMidnight == bus.arrivingAfterMidnight &&
                Objects.equals(serviceName, bus.serviceName) &&
                Objects.equals(departureTime, bus.departureTime) &&
                Objects.equals(arrivalTime, bus.arrivalTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceName, departureTime, arrivalTime, duration, arrivingAfterMidnight);
    }
}
