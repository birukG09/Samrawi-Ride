package persons;

public class RegularPassenger extends Passenger {

    public RegularPassenger(String name, int age, String nationalID, String phone, String email,
                            String username, String password, String preferredArea) {
        super(name, age, nationalID, phone, email, username, password, preferredArea);
        this.isPriority = false;
    }

    @Override
    public void requestRide(system.SamrawiSystem system) {
        system.addPassengerToQueue(this);
        System.out.println("  [Queue] " + username + " added to the standard queue.");
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("  Type         : Regular Passenger");
    }
}
