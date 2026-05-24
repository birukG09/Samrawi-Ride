package persons;

public class PriorityPassenger extends Passenger {

    public PriorityPassenger(String name, int age, String nationalID, String phone, String email,
                             String username, String password, String preferredArea) {
        super(name, age, nationalID, phone, email, username, password, preferredArea);
        this.isPriority = true;
    }

    @Override
    public void requestRide(system.SamrawiSystem system) {
        system.addPriorityPassengerToFront(this);
        System.out.println("  [Priority] " + username + " placed at the FRONT of the queue.");
    }

    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("  Type         : Priority Passenger (Elderly/Disabled)");
        System.out.println("  Queue Status : Front of line");
    }
}
