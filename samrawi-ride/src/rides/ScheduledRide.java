package rides;

import models.Route;
import persons.Driver;
import persons.Passenger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduledRide extends Ride {

    private LocalDateTime scheduledTime;
    private boolean reminderSent;

    public ScheduledRide(Route route, Driver driver, Passenger passenger, LocalDateTime scheduledTime) {
        super(route, driver, passenger);
        this.scheduledTime = scheduledTime;
        this.reminderSent = false;
    }

    public LocalDateTime getScheduledTime() { return scheduledTime; }
    public boolean isReminderSent() { return reminderSent; }

    public void sendReminder() {
        reminderSent = true;
        System.out.println("  [Reminder] Reminder sent to " + passenger.getUsername()
                + " — ride at " + scheduledTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }

    @Override
    public void confirmRide() {
        super.confirmRide();
        sendReminder();
        System.out.println("  [Scheduled] Ride booked for "
                + scheduledTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm")));
    }

    @Override
    public String toString() {
        return super.toString() + " | Scheduled: "
                + scheduledTime.format(DateTimeFormatter.ofPattern("MMM dd HH:mm"));
    }
}
