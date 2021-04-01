package by.duallab.testtask.bus;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

import static by.duallab.testtask.app.ApplicationConstants.*;

public class Bus {
    private final String serviceName;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;
    private final long duration;
    private boolean arrivingAfterMidnight;

    public Bus(String serviceName, LocalTime departureTime, LocalTime arrivalTime) {
        this.serviceName = serviceName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        arrivingAfterMidnight = arrivalTime.isBefore(departureTime);
        this.duration = calculateDuration(departureTime, arrivalTime);
    }

    private long calculateDuration(LocalTime departureTime, LocalTime arrivalTime) {
        long tmpDuration = Duration.between(departureTime, arrivalTime).toMinutes();
        tmpDuration = (tmpDuration < 0) ? MINUTES_IN_HOUR * HOURS_IN_DAY + tmpDuration : tmpDuration;
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

    public int compareBuses(Bus bus) {
        if (this.equals(bus)) {
            return 1;
        }
        if (this.getDepartureTime().equals(bus.getDepartureTime())) {
            return compareBusesWithSameDepartureTime(bus);
        }
        if (this.getArrivalTime().equals(bus.getArrivalTime())) {
            return compareBusesWithSameArrivalTime(bus);
        }
        return compareBusesWithPossibleLaterDepartureAndEarlierArrival(bus);
    }

    private int compareBusesWithSameDepartureTime(Bus bus) {
        if (this.getDuration() < bus.getDuration()) {
            return 1;
        } else if (this.getDuration() == bus.getDuration()) {
            if (this.getServiceName().equals(SERVICE_POSH_NAME)) return 1;
            return -1;
        } else {
            return -1;
        }
    }

    private int compareBusesWithSameArrivalTime(Bus bus) {
        if (this.getDuration() < bus.getDuration()) {
            return 1;
        } else {
            return -1;
        }
    }

    private int compareBusesWithPossibleLaterDepartureAndEarlierArrival(Bus bus) {
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

    @Override
    public String toString() {
        return serviceName + SPACE + departureTime.format(FORMAT_TO_PRINT) + SPACE
                + arrivalTime.format(FORMAT_TO_PRINT);

    }
}
