package system;

import rides.Ride;
import persons.Driver;
import persons.Passenger;

import java.util.List;
import java.util.stream.Collectors;

public class ReportGenerator {

    public void generateReport(List<Ride> rides) {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.println("  ║    SAMRAWI RIDE — FULL REPORT        ║");
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println("  Total Rides     : " + rides.size());
        long completed = rides.stream().filter(r -> r.getStatus() == Ride.Status.COMPLETED).count();
        long cancelled = rides.stream().filter(r -> r.getStatus() == Ride.Status.CANCELLED).count();
        long active    = rides.stream().filter(r -> r.getStatus() == Ride.Status.IN_PROGRESS).count();
        System.out.println("  Completed       : " + completed);
        System.out.println("  Cancelled       : " + cancelled);
        System.out.println("  In Progress     : " + active);
        System.out.println();
        if (!rides.isEmpty()) {
            System.out.println("  Recent rides:");
            rides.stream().limit(5).forEach(r -> System.out.println("    " + r));
        }
    }

    public void generateReport(List<Ride> rides, String area) {
        List<Ride> filtered = rides.stream()
                .filter(r -> r.getRoute().getOrigin().equalsIgnoreCase(area)
                          || r.getRoute().getDestination().equalsIgnoreCase(area))
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.printf ("  ║  AREA REPORT: %-22s║%n", area);
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println("  Rides in/through " + area + " : " + filtered.size());
        filtered.forEach(r -> System.out.println("    " + r));
        if (filtered.isEmpty()) System.out.println("  No rides found for area: " + area);
    }

    public void generateReport(List<Ride> rides, int month) {
        List<Ride> filtered = rides.stream()
                .filter(r -> r.getDate().getMonthValue() == month)
                .collect(Collectors.toList());

        String[] months = {"", "January","February","March","April","May","June",
                           "July","August","September","October","November","December"};
        String monthName = (month >= 1 && month <= 12) ? months[month] : "Unknown";

        System.out.println();
        System.out.println("  ╔══════════════════════════════════════╗");
        System.out.printf ("  ║  MONTHLY REPORT: %-20s║%n", monthName);
        System.out.println("  ╚══════════════════════════════════════╝");
        System.out.println("  Total rides in " + monthName + " : " + filtered.size());
        long completed = filtered.stream().filter(r -> r.getStatus() == Ride.Status.COMPLETED).count();
        System.out.println("  Completed : " + completed);
        filtered.forEach(r -> System.out.println("    " + r));
        if (filtered.isEmpty()) System.out.println("  No rides found for month: " + monthName);
    }
}
