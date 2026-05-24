import models.Route;
import models.Vehicle;
import models.Rating;
import models.SafetyFlag;
import persons.*;
import rides.*;
import system.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static SamrawiSystem system = new SamrawiSystem();
    static SafetyManager safety = new SafetyManager();
    static ReportGenerator reporter = new ReportGenerator();
    static Scanner scanner = new Scanner(System.in);

    static List<User> allUsers = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println();
        System.out.println("  ╔════════════════════════════════════════════╗");
        System.out.println("  ║       ሳምራዊ  SAMRAWI RIDE                  ║");
        System.out.println("  ║  Free rides. Rooted in faith.              ║");
        System.out.println("  ║  Built for Ethiopia.                       ║");
        System.out.println("  ╚════════════════════════════════════════════╝");

        System.out.println();
        System.out.println("  ── OOP Demo: Superclass Reference Array ──");
        Vehicle sampleVehicle = new Vehicle("3-AA-1234", "Toyota Corolla", "White", 3);
        User[] users = {
            new VerifiedDriver("Abebe Girma", 34, "ETH-001", "0911-111-111",
                    "abebe@email.com", "abebe_driver", "pass123", sampleVehicle, "LIC-9001"),
            new NewDriver("Tigist Haile", 26, "ETH-002", "0922-222-222",
                    "tigist@email.com", "tigist_driver", "pass456",
                    new Vehicle("4-BB-5678", "Bajaj", "Blue", 2), "LIC-9002"),
            new RegularPassenger("Mekdes Wolde", 22, "ETH-003", "0933-333-333",
                    "mekdes@email.com", "mekdes_pass", "pass789", "Bole"),
            new PriorityPassenger("Ato Lemma", 72, "ETH-004", "0944-444-444",
                    "lemma@email.com", "lemma_elder", "passEld", "Piassa")
        };

        for (User u : users) {
            System.out.println();
            u.displayProfile();
        }
        System.out.println();
        System.out.println("  (End of OOP demo — entering main menu)");
        System.out.println();

        for (User u : users) {
            allUsers.add(u);
            if (u instanceof Driver) system.registerDriver((Driver) u);
        }

        String choice;
        do {
            printMenu();
            System.out.print("  > ");
            choice = scanner.nextLine().trim().toUpperCase();
            System.out.println();
            switch (choice) {
                case "1": registerDriver(); break;
                case "2": registerPassenger(); break;
                case "3": offerRide(); break;
                case "4": requestRide(); break;
                case "5": matchPassengers(); break;
                case "6": confirmOrDecline(); break;
                case "7": completeAndRate(); break;
                case "8": viewProfile(); break;
                case "9": viewFlaggedUsers(); break;
                case "R": generateReportMenu(); break;
                case "0": System.out.println("  Ciao! ሰላምታ። "); break;
                default:  System.out.println("  Invalid option. Please try again.");
            }
        } while (!choice.equals("0"));

        scanner.close();
    }

    static void printMenu() {
        System.out.println("  ┌─────────────────────────────────────────┐");
        System.out.println("  │          SAMRAWI RIDE — MENU            │");
        System.out.println("  ├─────────────────────────────────────────┤");
        System.out.println("  │  [1]  Register as Driver                │");
        System.out.println("  │  [2]  Register as Passenger             │");
        System.out.println("  │  [3]  Offer a Ride (Driver)             │");
        System.out.println("  │  [4]  Request a Ride (Passenger)        │");
        System.out.println("  │  [5]  Match Passengers to Drivers       │");
        System.out.println("  │  [6]  Confirm or Decline a Ride Request │");
        System.out.println("  │  [7]  Complete Ride & Rate              │");
        System.out.println("  │  [8]  View User Profile & Safety Score  │");
        System.out.println("  │  [9]  View Flagged & Blocked Users      │");
        System.out.println("  │  [R]  Generate Report                   │");
        System.out.println("  │  [0]  Exit                              │");
        System.out.println("  └─────────────────────────────────────────┘");
    }

    static void registerDriver() {
        System.out.println("  ── Register as Driver ──────────────────────");
        System.out.print("  Name         : "); String name = scanner.nextLine();
        System.out.print("  Age          : "); int age = intInput();
        System.out.print("  National ID  : "); String nid = scanner.nextLine();
        System.out.print("  Phone        : "); String phone = scanner.nextLine();
        System.out.print("  Email        : "); String email = scanner.nextLine();
        System.out.print("  Username     : "); String username = scanner.nextLine();
        System.out.print("  Password     : "); String password = scanner.nextLine();
        System.out.print("  License No.  : "); String license = scanner.nextLine();
        System.out.print("  Plate No.    : "); String plate = scanner.nextLine();
        System.out.print("  Car Model    : "); String model = scanner.nextLine();
        System.out.print("  Car Color    : "); String color = scanner.nextLine();
        System.out.print("  Available Seats: "); int seats = intInput();
        System.out.print("  Verified? (y/n): "); boolean verified = scanner.nextLine().trim().equalsIgnoreCase("y");

        Vehicle v = new Vehicle(plate, model, color, seats);
        Driver driver = verified
                ? new VerifiedDriver(name, age, nid, phone, email, username, password, v, license)
                : new NewDriver(name, age, nid, phone, email, username, password, v, license);

        system.registerDriver(driver);
        allUsers.add(driver);
        System.out.println("  Driver registered successfully!");
    }

    static void registerPassenger() {
        System.out.println("  ── Register as Passenger ───────────────────");
        System.out.print("  Name         : "); String name = scanner.nextLine();
        System.out.print("  Age          : "); int age = intInput();
        System.out.print("  National ID  : "); String nid = scanner.nextLine();
        System.out.print("  Phone        : "); String phone = scanner.nextLine();
        System.out.print("  Email        : "); String email = scanner.nextLine();
        System.out.print("  Username     : "); String username = scanner.nextLine();
        System.out.print("  Password     : "); String password = scanner.nextLine();
        System.out.print("  Preferred Area (e.g. Bole, Piassa): "); String area = scanner.nextLine();
        System.out.print("  Priority passenger (elderly/disabled)? (y/n): ");
        boolean priority = scanner.nextLine().trim().equalsIgnoreCase("y");

        Passenger passenger = priority
                ? new PriorityPassenger(name, age, nid, phone, email, username, password, area)
                : new RegularPassenger(name, age, nid, phone, email, username, password, area);

        allUsers.add(passenger);
        System.out.println("  Passenger registered: " + username
                + (priority ? " [PRIORITY]" : " [Regular]"));
    }

    static void offerRide() {
        System.out.println("  ── Offer a Ride ────────────────────────────");
        System.out.print("  Driver username: "); String uname = scanner.nextLine();
        Driver driver = system.findDriver(uname);
        if (driver == null) { System.out.println("  Driver not found."); return; }
        if (driver.isBlocked()) { System.out.println("  This driver is blocked."); return; }
        if (driver instanceof NewDriver && !((NewDriver) driver).canOfferRide()) {
            System.out.println("  Daily ride limit reached for new driver."); return;
        }

        System.out.print("  Origin (e.g. Bole): "); String origin = scanner.nextLine();
        System.out.print("  Destination       : "); String dest = scanner.nextLine();
        System.out.print("  Distance (km)     : "); double dist = doubleInput();

        Route route = new Route(origin, dest, dist);
        driver.setCurrentRoute(route);
        if (driver instanceof NewDriver) ((NewDriver) driver).incrementDailyRides();
        System.out.println("  Ride offered on route: " + route);
    }

    static void requestRide() {
        System.out.println("  ── Request a Ride ──────────────────────────");
        System.out.print("  Passenger username: "); String uname = scanner.nextLine();
        Passenger passenger = findPassenger(uname);
        if (passenger == null) { System.out.println("  Passenger not found."); return; }
        if (passenger.isBlocked()) { System.out.println("  This passenger is blocked."); return; }

        passenger.requestRide(system);
        System.out.print("  Preferred area confirmed as: " + passenger.getPreferredArea());
        System.out.println();
    }

    static void matchPassengers() {
        System.out.println("  ── Match Passengers to Drivers ─────────────");
        system.printQueue();
        Ride ride = system.matchNextPassenger();
        if (ride != null) {
            System.out.println("  Matched ride: " + ride);
        }
    }

    static void confirmOrDecline() {
        System.out.println("  ── Confirm or Decline Ride ─────────────────");
        List<Ride> rides = system.getAllRides();
        if (rides.isEmpty()) { System.out.println("  No rides in the system yet."); return; }

        System.out.println("  Current rides:");
        for (int i = 0; i < rides.size(); i++) {
            System.out.println("    [" + i + "] " + rides.get(i));
        }
        System.out.print("  Enter ride index: "); int idx = intInput();
        if (idx < 0 || idx >= rides.size()) { System.out.println("  Invalid index."); return; }
        Ride ride = rides.get(idx);

        System.out.print("  Confirm (c) or Decline (d)? ");
        String action = scanner.nextLine().trim().toLowerCase();
        if (action.equals("c")) {
            ride.confirmRide();
        } else {
            ride.cancelRide();
        }
    }

    static void completeAndRate() {
        System.out.println("  ── Complete Ride & Rate ────────────────────");
        List<Ride> rides = system.getAllRides();
        if (rides.isEmpty()) { System.out.println("  No rides to complete."); return; }

        List<Ride> confirmed = new ArrayList<>();
        for (Ride r : rides) if (r.getStatus() == Ride.Status.CONFIRMED) confirmed.add(r);
        if (confirmed.isEmpty()) { System.out.println("  No confirmed rides to complete."); return; }

        System.out.println("  Confirmed rides:");
        for (int i = 0; i < confirmed.size(); i++) System.out.println("    [" + i + "] " + confirmed.get(i));
        System.out.print("  Enter ride index: "); int idx = intInput();
        if (idx < 0 || idx >= confirmed.size()) { System.out.println("  Invalid index."); return; }

        Ride ride = confirmed.get(idx);
        ride.completeRide();
        ride.getDriver().clearRide();

        System.out.println("  Rate the driver (1-5): ");
        System.out.print("  Score  : "); int score = intInput();
        System.out.print("  Comment: "); String comment = scanner.nextLine();
        Rating ratingForDriver = new Rating(score, comment, ride.getPassenger().getUsername());
        ride.getDriver().addRating(ratingForDriver);
        safety.updateSafetyScore(ride.getDriver(), score);
        System.out.println("  Rating saved: " + ratingForDriver);

        System.out.println("  Rate the passenger (1-5):");
        System.out.print("  Score  : "); int pscore = intInput();
        System.out.print("  Comment: "); String pcomment = scanner.nextLine();
        Rating ratingForPassenger = new Rating(pscore, pcomment, ride.getDriver().getUsername());
        ride.getPassenger().addRating(ratingForPassenger);
        safety.updateSafetyScore(ride.getPassenger(), pscore);
        System.out.println("  Rating saved: " + ratingForPassenger);
    }

    static void viewProfile() {
        System.out.println("  ── View User Profile ───────────────────────");
        System.out.print("  Enter username: "); String uname = scanner.nextLine();
        User user = allUsers.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(uname))
                .findFirst().orElse(null);
        if (user == null) { System.out.println("  User not found."); return; }
        System.out.println();
        user.displayProfile();
        if (!user.getRatings().isEmpty()) {
            System.out.println("  Recent Ratings:");
            user.getRatings().stream().limit(3).forEach(r -> System.out.println("    " + r));
        }
    }

    static void viewFlaggedUsers() {
        System.out.println("  ── Flagged & Blocked Users ─────────────────");

        if (allUsers.stream().allMatch(u -> u.getFlags().isEmpty())) {
            System.out.println("  (Demo: flagging seed user to show system in action)");
            safety.flagUser(allUsers.get(0), "Late cancellation", "system", SafetyFlag.Severity.LOW);
        }
        safety.printFlaggedUsers(allUsers);
    }

    static void generateReportMenu() {
        System.out.println("  ── Generate Report ─────────────────────────");
        System.out.println("  [1] Full system report");
        System.out.println("  [2] By area");
        System.out.println("  [3] By month");
        System.out.print("  Choice: "); String choice = scanner.nextLine().trim();

        List<Ride> rides = system.getAllRides();

        switch (choice) {
            case "1":
                reporter.generateReport(rides);
                break;
            case "2":
                System.out.print("  Enter area (e.g. Bole, Piassa): ");
                String area = scanner.nextLine();
                reporter.generateReport(rides, area);
                break;
            case "3":
                System.out.print("  Enter month number (1-12): ");
                int month = intInput();
                reporter.generateReport(rides, month);
                break;
            default:
                System.out.println("  Invalid choice.");
        }
    }

    static int intInput() {
        while (true) {
            try {
                int val = Integer.parseInt(scanner.nextLine().trim());
                return val;
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a number: ");
            }
        }
    }

    static double doubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  Please enter a number: ");
            }
        }
    }

    static Passenger findPassenger(String username) {
        return (Passenger) allUsers.stream()
                .filter(u -> u instanceof Passenger && u.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }
}
