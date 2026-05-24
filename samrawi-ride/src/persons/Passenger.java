package persons;

import rides.Ride;
import java.util.ArrayList;
import java.util.List;

public class Passenger extends User {

    protected List<Ride> rideHistory;
    protected String preferredArea;
    protected boolean isPriority;

    public Passenger(String name, int age, String nationalID, String phone, String email,
                     String username, String password, String preferredArea) {
        super(name, age, nationalID, phone, email, username, password);
        this.rideHistory = new ArrayList<>();
        this.preferredArea = preferredArea;
        this.isPriority = false;
    }

    public List<Ride> getRideHistory() { return rideHistory; }
    public String getPreferredArea() { return preferredArea; }
    public boolean isPriority() { return isPriority; }

    public void addRideToHistory(Ride ride) {
        rideHistory.add(ride);
    }

    public void requestRide(system.SamrawiSystem system) {
        system.addPassengerToQueue(this);
        System.out.println("  [Queue] " + username + " added to standard queue.");
    }

    @Override
    public void displayProfile() {
        System.out.println("  ── Passenger Profile ───────────────────");
        super.displayProfile();
        System.out.println("  Area         : " + preferredArea);
        System.out.println("  Rides Taken  : " + rideHistory.size());
        System.out.println("  Priority     : " + (isPriority ? "Yes (front of queue)" : "No"));
    }
}
