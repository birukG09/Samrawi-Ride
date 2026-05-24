package models;

public class Route {

    private String origin;
    private String destination;
    private double estimatedDistanceKm;

    public Route(String origin, String destination, double estimatedDistanceKm) {
        this.origin = origin;
        this.destination = destination;
        this.estimatedDistanceKm = estimatedDistanceKm;
    }

    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public double getEstimatedDistanceKm() { return estimatedDistanceKm; }

    public boolean matchesRoute(Route other) {
        return this.origin.equalsIgnoreCase(other.origin)
                && this.destination.equalsIgnoreCase(other.destination);
    }

    @Override
    public String toString() {
        return origin + " → " + destination + " (" + estimatedDistanceKm + " km)";
    }
}
