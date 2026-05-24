package persons;

import models.Vehicle;
import java.time.LocalDate;

public class VerifiedDriver extends Driver {

    private LocalDate verificationDate;
    private String trustBadge;

    public VerifiedDriver(String name, int age, String nationalID, String phone, String email,
                          String username, String password,
                          Vehicle vehicle, String licenseNumber) {
        super(name, age, nationalID, phone, email, username, password, vehicle, licenseNumber);
        this.isVerified = true;
        this.verificationDate = LocalDate.now();
        this.trustBadge = "✔ Samrawi Verified";
    }

    public LocalDate getVerificationDate() { return verificationDate; }
    public String getTrustBadge() { return trustBadge; }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("  Badge        : " + trustBadge);
        System.out.println("  Verified On  : " + verificationDate);
        System.out.println("  Ride Limit   : Unlimited");
    }
}
