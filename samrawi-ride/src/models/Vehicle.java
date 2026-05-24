package models;

public class Vehicle {

    private String plateNumber;
    private String model;
    private String color;
    private int availableSeats;

    public Vehicle(String plateNumber, String model, String color, int availableSeats) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.color = color;
        this.availableSeats = availableSeats;
    }

    public String getPlateNumber() { return plateNumber; }
    public String getModel() { return model; }
    public String getColor() { return color; }

    public int getAvailableSeats() { return availableSeats; }

    public void setAvailableSeats(int seats) {
        if (seats >= 0) {
            this.availableSeats = seats;
        }
    }

    @Override
    public String toString() {
        return color + " " + model + " [" + plateNumber + "] — " + availableSeats + " seat(s) available";
    }
}
