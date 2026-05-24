package persons;

import models.Vehicle;

public class NewDriver extends Driver {

    private int dailyRideCount;
    private static final int MAX_RIDES_PER_DAY = 3;

    public NewDriver(String name, int age, String nationalID, String phone, String email,
                     String username, String password,
                     Vehicle vehicle, String licenseNumber) {
        super(name, age, nationalID, phone, email, username, password, vehicle, licenseNumber);
        this.dailyRideCount = 0;
        this.isVerified = false;
    }

    public boolean canOfferRide() {
        return dailyRideCount < MAX_RIDES_PER_DAY;
    }

    public void incrementDailyRides() {
        if (canOfferRide()) {
            dailyRideCount++;
        }
    }

    public void resetDailyCount() {
        dailyRideCount = 0;
    }

    public int getDailyRideCount() { return dailyRideCount; }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("  Status       : Probationary Driver");
        System.out.println("  Rides Today  : " + dailyRideCount + " / " + MAX_RIDES_PER_DAY);
        System.out.println("  Ride Limit   : " + MAX_RIDES_PER_DAY + " per day");
    }
}
