package rides;

import models.Route;
import persons.Driver;
import persons.Passenger;
import java.time.LocalDate;

public class Ride {

    public enum Status { REQUESTED, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED }

    private static int idCounter = 1000;
    protected String rideID;
    protected Route route;
    protected Driver driver;
    protected Passenger passenger;
    protected Status status;
    protected LocalDate date;

    public Ride(Route route, Driver driver, Passenger passenger) {
        this.rideID = "RIDE-" + (++idCounter);
        this.route = route;
        this.driver = driver;
        this.passenger = passenger;
        this.status = Status.REQUESTED;
        this.date = LocalDate.now();
    }

    public String getRideID() { return rideID; }
    public Route getRoute() { return route; }
    public Driver getDriver() { return driver; }
    public Passenger getPassenger() { return passenger; }
    public Status getStatus() { return status; }
    public LocalDate getDate() { return date; }

    public void setStatus(Status status) { this.status = status; }

    public void confirmRide() {
        this.status = Status.CONFIRMED;
        System.out.println("  [Ride] " + rideID + " confirmed.");
        System.out.println("  Driver phone now visible: " + driver.getPhone());
    }

    public void completeRide() {
        this.status = Status.COMPLETED;
        System.out.println("  [Ride] " + rideID + " completed. Safe arrival!");
    }

    public void cancelRide() {
        this.status = Status.CANCELLED;
        System.out.println("  [Ride] " + rideID + " cancelled.");
    }

    @Override
    public String toString() {
        return rideID + " | " + route + " | Driver: " + driver.getUsername()
                + " | Passenger: " + passenger.getUsername() + " | " + status;
    }
}
