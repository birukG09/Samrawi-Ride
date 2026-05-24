package system;

import models.Route;
import persons.Driver;
import persons.Passenger;
import rides.InstantRide;
import rides.Ride;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SamrawiSystem {

    private List<Driver> drivers;
    private Queue<Passenger> passengerQueue;
    private List<Ride> allRides;

    public SamrawiSystem() {
        this.drivers = new ArrayList<>();
        this.passengerQueue = new LinkedList<>();
        this.allRides = new ArrayList<>();
    }

    public void registerDriver(Driver driver) {
        drivers.add(driver);
        System.out.println("  [System] Driver registered: " + driver.getUsername());
    }

    public void addPassengerToQueue(Passenger passenger) {
        passengerQueue.offer(passenger);
    }

    public void addPriorityPassengerToFront(Passenger passenger) {
        List<Passenger> temp = new ArrayList<>(passengerQueue);
        passengerQueue.clear();
        passengerQueue.offer(passenger);
        passengerQueue.addAll(temp);
    }

    public Ride matchNextPassenger() {
        if (passengerQueue.isEmpty()) {
            System.out.println("  [Match] No passengers in queue.");
            return null;
        }

        Passenger passenger = passengerQueue.peek();
        Route needed = new Route(passenger.getPreferredArea(), "Destination", 0);

        for (Driver driver : drivers) {
            if (!driver.isBlocked() && driver.isActive() && driver.hasActiveRide()) {
                Route offered = driver.getCurrentRoute();
                if (offered != null && offered.getOrigin().equalsIgnoreCase(needed.getOrigin())) {
                    passengerQueue.poll();
                    InstantRide ride = new InstantRide(offered, driver, passenger);
                    allRides.add(ride);
                    passenger.addRideToHistory(ride);
                    System.out.println("  [Match] " + passenger.getUsername()
                            + " matched with driver " + driver.getUsername());
                    return ride;
                }
            }
        }

        System.out.println("  [Match] No available driver found for route from: "
                + passenger.getPreferredArea());
        return null;
    }

    public List<Driver> getDrivers() { return drivers; }
    public Queue<Passenger> getPassengerQueue() { return passengerQueue; }
    public List<Ride> getAllRides() { return allRides; }

    public void addRide(Ride ride) { allRides.add(ride); }

    public Driver findDriver(String username) {
        return drivers.stream()
                .filter(d -> d.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    public void printQueue() {
        if (passengerQueue.isEmpty()) {
            System.out.println("  [Queue] No passengers waiting.");
            return;
        }
        System.out.println("  [Queue] Passengers waiting (" + passengerQueue.size() + "):");
        int i = 1;
        for (Passenger p : passengerQueue) {
            System.out.println("    " + i++ + ". " + p.getUsername()
                    + (p.isPriority() ? " [PRIORITY]" : "") + " — from " + p.getPreferredArea());
        }
    }
}
