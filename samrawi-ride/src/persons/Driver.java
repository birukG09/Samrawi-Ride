package persons;

import models.Route;
import models.Vehicle;
import java.util.ArrayList;
import java.util.List;

public class Driver extends User {

    protected Vehicle vehicle;
    protected String licenseNumber;
    protected boolean isVerified;
    protected List<Route> routeHistory;
    protected Route currentRoute;
    protected boolean hasActiveRide;

    public Driver(String name, int age, String nationalID, String phone, String email,
                  String username, String password,
                  Vehicle vehicle, String licenseNumber) {
        super(name, age, nationalID, phone, email, username, password);
        this.vehicle = vehicle;
        this.licenseNumber = licenseNumber;
        this.isVerified = false;
        this.routeHistory = new ArrayList<>();
        this.hasActiveRide = false;
    }

    public Vehicle getVehicle() { return vehicle; }
    public String getLicenseNumber() { return licenseNumber; }
    public boolean isVerified() { return isVerified; }
    public Route getCurrentRoute() { return currentRoute; }
    public boolean hasActiveRide() { return hasActiveRide; }
    public List<Route> getRouteHistory() { return routeHistory; }

    public void setCurrentRoute(Route route) {
        this.currentRoute = route;
        this.hasActiveRide = true;
        routeHistory.add(route);
    }

    public void clearRide() { this.hasActiveRide = false; this.currentRoute = null; }

    @Override
    public void displayProfile() {
        System.out.println("  ── Driver Profile ──────────────────────");
        super.displayProfile();
        System.out.println("  License      : " + licenseNumber);
        System.out.println("  Vehicle      : " + vehicle);
        System.out.println("  Verified     : " + (isVerified ? "Yes" : "No"));
        System.out.println("  Routes Driven: " + routeHistory.size());
    }
}
