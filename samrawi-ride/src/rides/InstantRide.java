package rides;

import models.Route;
import persons.Driver;
import persons.Passenger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InstantRide extends Ride {

    private LocalDateTime requestTime;

    public InstantRide(Route route, Driver driver, Passenger passenger) {
        super(route, driver, passenger);
        this.requestTime = LocalDateTime.now();
    }

    public LocalDateTime getRequestTime() { return requestTime; }

    @Override
    public void confirmRide() {
        super.confirmRide();
        System.out.println("  [Instant] Driver dispatched NOW — requested at "
                + requestTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }

    @Override
    public String toString() {
        return super.toString() + " | Instant — requested at "
                + requestTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
